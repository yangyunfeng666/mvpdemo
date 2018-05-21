package com.yunsoft.mvpdemo.commponent.lifecycle.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.kye.basemodule.network.base.BaseResponse;
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
    public LiveData<ApiResponse<BaseResponse<LocalUserInfo>>> getLoginUser(String telephone, String password, String longitude, String latitude, String JpushId) throws IOException {
        Call<BaseResponse<LocalUserInfo>> localUserInfoCall = RetrofitSourceFactory.createRetrofitApi(WebServiceApi.class).phonelogin(telephone, password, longitude, latitude, JpushId);
        MediatorLiveData<ApiResponse<BaseResponse<LocalUserInfo>>> mediatorLiveData = new MediatorLiveData<>();
        Response<BaseResponse<LocalUserInfo>> response = localUserInfoCall.execute();
        ApiResponse<BaseResponse<LocalUserInfo>> userInfoApiResponse = new ApiResponse<BaseResponse<LocalUserInfo>>() {
            @Override
            public BaseResponse<LocalUserInfo> getBody() {
                return response.body();
            }

            @Override
            public String getErrorMsg() {
                return response.message();
            }

            @Override
            public boolean isSuccessful() {
                return response.isSuccessful();
            }
        };
        mediatorLiveData.postValue(userInfoApiResponse);
        return mediatorLiveData;
    }
}
