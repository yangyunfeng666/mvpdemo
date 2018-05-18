package com.yunsoft.mvpdemo.data.source;

import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-17 16:42
 * Description:this is WebService
 */

public interface WebService {
    @GET("/users/{user}")
    Call<LocalUser> getUser(@Path("user") String userId);
}
