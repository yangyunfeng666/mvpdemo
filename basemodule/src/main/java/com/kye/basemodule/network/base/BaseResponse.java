package com.kye.basemodule.network.base;

/**
 * Created by yyf on 2018-04-11 12:11.
 * 定义的返回数据体
 */

public class BaseResponse<T> {
    /**
     * 服务返回码
     */
    private int code;
    /**
     * 返回的错误信息
     */
    private String errMsg;
    /**
     * 返回的数据部分
     */
    private T datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
