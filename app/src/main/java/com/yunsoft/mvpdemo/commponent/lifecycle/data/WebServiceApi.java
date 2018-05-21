package com.yunsoft.mvpdemo.commponent.lifecycle.data;

import android.arch.lifecycle.LiveData;

import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-17 16:42
 * Description:this is WebService
 */

public interface WebServiceApi {

    @POST("user/login.do")
    @FormUrlEncoded
        //中文乱码问题
    Call<BaseResponse<LocalUserInfo>> phonelogin(@Field("telephone") String telephone,
                                                                @Field("password") String password,
                                                                @Field("longitude") String longitude,
                                                                @Field("latitude") String latitude,
                                                                @Field("JpushId") String JpushId);

}
