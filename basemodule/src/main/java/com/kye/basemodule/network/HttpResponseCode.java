package com.kye.basemodule.network;

/**
 * 网络返回码
 */

public class HttpResponseCode {
    //没有网络
    public static final int CODE_NO_NETWORK = 200;
    //没有数据返回
    public static final int CODE_NO_DATA = 201;
    //数据格式不正确
    public static final int CODE_NO_FULL = 201;
    //成功
    public static final int CODE_SUCCESS = 0;
    //未找到数据
    public static final int CODE_NULL = 800;
    //token有问题
    public static final int CODE_TOKEN_EXPIRED = 401;
}
