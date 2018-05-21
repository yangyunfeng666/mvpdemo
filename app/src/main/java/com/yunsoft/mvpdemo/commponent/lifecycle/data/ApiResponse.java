package com.yunsoft.mvpdemo.commponent.lifecycle.data;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 16:28
 * Description:this is ApiResponse
 */

public interface  ApiResponse<ResultType> {
    ResultType getBody();
    String getErrorMsg();
    boolean isSuccessful();
}
