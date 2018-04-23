package com.yunsoft.mvpdemo.datamanager;

import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.http.retrofit.RetrofitSourceFactory;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

import io.reactivex.Observable;


/**
 * Created by yyf on 2018-04-11 17:14.
 * 定义范围服务器的请求管理类
 */

public class MainDataManager {
    //登陆为例
    public Observable<BaseResponse<LocalUserInfo>> phonelogin(String telephone, String password, String longitude, String latitude, String JpushId) {
        return RetrofitSourceFactory.getInstance().phonelogin(telephone, password,longitude,latitude,JpushId);
    }
}
