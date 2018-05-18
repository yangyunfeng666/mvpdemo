package com.yunsoft.mvpdemo.commponent.lifecycle.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;


/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 16:17
 * Description:this is AbsDataSource
 */

public abstract class NetworkBoundResource<ResultType,RequestType> {

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    // Called with the data in the database to decide whether it should be
    // fetched from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    // Called to get the cached data from the database
    @NonNull @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // Called to create the API call.
    @NonNull @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected abstract void onFetchFailed();

    @MainThread
    NetworkBoundResource(){
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dataSoruce = loadFromDb();
        result.addSource(dataSoruce, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                result.removeSource(dataSoruce);
                if(shouldFetch(resultType)){//是否网络加载
                    fetchDataFromNetwork(dataSoruce);
                }else{
                    //设置本地加载数据
                    result.addSource(dataSoruce,newdata->result.setValue(Resource.success(newdata)));
                }
            }
        });
    }
    private void fetchDataFromNetwork(LiveData<ResultType> dbSource){
        LiveData<ApiResponse<RequestType>> apiResponseLiveData = createCall();
        result.addSource(dbSource,newData->result.setValue(Resource.loading(newData)));
//        result.removeSource(dbSource);
        result.removeSource(apiResponseLiveData);
        if(apiResponseLiveData.getValue().code()==200){
            saveResultAndReInit(apiResponseLiveData.getValue());
        }else{
            onFetchFailed();
            result.addSource(dbSource,newData->result.setValue(Resource.error(apiResponseLiveData.getValue().errMsg(),newData)));
        }
    }

    @MainThread
    private void saveResultAndReInit(ApiResponse<RequestType> response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response.datas());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // we specially request a new live data,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                result.addSource(loadFromDb(),
                        newData -> result.setValue(Resource.success(newData)));
            }
        }.execute();
    }



    // returns a LiveData that represents the resource, implemented
    // in the base class.
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

}
