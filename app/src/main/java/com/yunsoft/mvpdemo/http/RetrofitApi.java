package com.yunsoft.mvpdemo.http;

import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yyf on 2018-04-11 11:59.
 * 定义一个retrofit的请求API地址
 * 这里些请求url
 */

public interface RetrofitApi {

    //手机号登陆接口  全路径接口：http://47.52.135.9:8686/Minority/mobile/interface/user/login.do
    @POST("user/login.do")
    @FormUrlEncoded
    //中文乱码问题
    Observable<BaseResponse<LocalUserInfo>> phonelogin(@Field("telephone") String telephone,
                                                       @Field("password") String password,
                                                       @Field("longitude") String longitude,
                                                       @Field("latitude") String latitude,
                                                       @Field("JpushId") String JpushId);

    //手机号登陆接口  全路径接口：http://47.52.135.9:8686/Minority/mobile/interface/user/login.do
    @POST("user/login.do")
    @FormUrlEncoded
    //中文乱码问题
    Observable<BaseResponse<List<LocalUserInfo>>> CallAllUser();

}
