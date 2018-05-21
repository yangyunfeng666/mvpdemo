package com.yunsoft.mvpdemo.commponent.lifecycle.data;

import android.arch.lifecycle.LiveData;

import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

import java.io.IOException;

import retrofit2.http.Field;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 15:57
 * Description:this is DataSource
 */
//定义数据接口类
public interface DataSource {

    LiveData<ApiResponse<BaseResponse<LocalUserInfo>>> getLoginUser(String telephone,
                                                                    String password,
                                                                    String longitude,
                                                                    String latitude,
                                                                    String JpushId) throws IOException;
}
