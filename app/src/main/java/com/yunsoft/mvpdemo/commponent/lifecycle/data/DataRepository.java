package com.yunsoft.mvpdemo.commponent.lifecycle.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yunsoft.mvpdemo.commponent.lifecycle.data.source.local.LocalDataSource;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.source.remote.RemoteDataSource;
import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

import java.io.IOException;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 15:52
 * Description:this is DataRepository
 */

public  class DataRepository  {
    public  static  volatile     DataRepository INSTANCE;
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;
    private AppExecutors executor;

    DataRepository(AppExecutors executor,LocalDataSource localDataSource, RemoteDataSource remoteDataSource){
        this.executor = executor;
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static DataRepository getInstance(AppExecutors executor,LocalDataSource tasksRemoteDataSource,
                                             RemoteDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(executor,tasksRemoteDataSource, tasksLocalDataSource);
                }
            }
        }
        return INSTANCE;
    }


    public LiveData<Resource<LocalUserInfo>> getLoginUser(String telephone, String password, String longitude, String latitude, String JpushId) {
        return  new NetworkBoundResource<LocalUserInfo,LocalUserInfo>(){
            @Override
            protected void saveCallResult(@NonNull LocalUserInfo item) {
                    //加载网络数据成功写数据库
            }

            @Override
            protected boolean shouldFetch(@Nullable LocalUserInfo data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<LocalUserInfo> loadFromDb() {
                return localDataSource.getLoginUser(telephone,password,longitude,latitude,JpushId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<LocalUserInfo>> createCall() {
                MediatorLiveData<ApiResponse<LocalUserInfo>> result = new MediatorLiveData<>();
                executor.networkIO().execute(new Runnable() {//切换线程执行
                    @Override
                    public void run() {
                        try {
                            result.addSource(remoteDataSource.getLoginUser(telephone, password, longitude, latitude, JpushId), new Observer<ApiResponse<LocalUserInfo>>() {
                                @Override
                                public void onChanged(@Nullable ApiResponse<LocalUserInfo> localUserInfoApiResponse) {
                                    result.setValue(localUserInfoApiResponse);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            onFetchFailed();
                        }
                    }
                });
             return result;
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }
}
