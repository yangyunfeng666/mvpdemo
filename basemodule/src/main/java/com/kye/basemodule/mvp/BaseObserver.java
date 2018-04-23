package com.kye.basemodule.mvp;

import android.content.res.Resources;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonSyntaxException;
import com.kye.basemodule.log.KyeLogUtils;
import com.kye.basemodule.network.HttpResponseCode;
import com.kye.basemodule.network.base.BaseResponse;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by yyf on 2018-04-11 12:23.
 */

public abstract class BaseObserver<E extends BaseResponse> implements Observer<E> {


    private BaseUiInterface mBaseUiInterface;

    private Disposable disposable;

    public BaseObserver(BaseUiInterface mBaseUiInterface) {
        this.mBaseUiInterface = mBaseUiInterface;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onComplete() {
        mBaseUiInterface.showLoadDataComplete();
        mBaseUiInterface.dismissLoadingDialog();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        mBaseUiInterface.dismissLoadingDialog();
        mBaseUiInterface.showLoadDataComplete();
        if(e==null|| e instanceof Resources.NotFoundException){
            mBaseUiInterface.showUnKnownException();
            return;
        }
        if(e instanceof SocketException ||e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException){
            mBaseUiInterface.showNetWorkException();
        }else if(e instanceof JsonSyntaxException || e instanceof NumberFormatException || e instanceof com.google.gson.stream.MalformedJsonException){
            mBaseUiInterface.showDataException("数据解析出错");
        }else if(e instanceof retrofit2.adapter.rxjava2.HttpException){
            mBaseUiInterface.showDataException("服务器出错（"+((retrofit2.adapter.rxjava2.HttpException) e).code()+"）");
        }else if(e  instanceof NullPointerException){
            mBaseUiInterface.showDataException("客户端开小差，工程师正在修复中...");
        }

    }

    @Override
    public void onNext(E response) {
        switch (response.getCode()){
                case HttpResponseCode.CODE_SUCCESS:
                onSuccess(response);
                break;
                case HttpResponseCode.CODE_TOKEN_EXPIRED://比如token 失效 返回登录页面这些
                //这里你里面具体定义的错误或者业务逻辑自己定义处理方式
                break;
                default:
                    onDataFailure(response);

        }
    }

    protected abstract void onSuccess(E response);


    public void onDataFailure(E response){
        KyeLogUtils.e(JSON.toJSON(response).toString());
        mBaseUiInterface.dismissLoadingDialog();
        String errMsg = response.getErrMsg();
        if(!TextUtils.isEmpty(errMsg)){
            mBaseUiInterface.showDataException(errMsg);
        }else{
            mBaseUiInterface.showUnKnownException();
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
