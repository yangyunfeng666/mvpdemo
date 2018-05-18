package com.yunsoft.mvpdemo.commponent.lifecycle.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.yunsoft.mvpdemo.commponent.lifecycle.data.ApiResponse;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataSource;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.WebServiceApi;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.http.retrofit.RetrofitSourceFactory;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 16:15
 * Description:this is RemoteDataSource
 */

public class RemoteDataSource implements DataSource {

   private  static volatile RemoteDataSource mInstance;

  public  static   RemoteDataSource getInstance(){
        if (mInstance==null){
            synchronized (RemoteDataSource.class){
                mInstance = new RemoteDataSource();
            }
        }
        return mInstance;
    }

    @Override
    public LiveData<ApiResponse<LocalUserInfo>> getLoginUser(String telephone, String password, String longitude, String latitude, String JpushId) throws IOException {
        Call<LocalUserInfo> localUserInfoCall = RetrofitSourceFactory.createRetrofitApi(WebServiceApi.class).phonelogin(telephone, password, longitude, latitude, JpushId);
        MediatorLiveData<ApiResponse<LocalUserInfo>> mediatorLiveData = new MediatorLiveData<>();
        Response<LocalUserInfo> response = localUserInfoCall.execute();
        ApiResponse<LocalUserInfo> userInfoApiResponse = new ApiResponse<LocalUserInfo>() {
            @Override
            public LocalUserInfo datas() {
                return response.body();
            }

            @Override
            public String errMsg() {
                return response.message();
            }

            @Override
            public int code() {
                return response.code();
            }
        };
        mediatorLiveData.postValue(userInfoApiResponse);
        return mediatorLiveData;
    }
}
