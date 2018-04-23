package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BaseObserver;
import com.kye.basemodule.mvp.BasePresenter;
import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.http.RetrofitApi;
import com.yunsoft.mvpdemo.http.retrofit.RetrofitSourceFactory;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yyf on 2018-04-11 17:03.
 */

public class SimplePresenter extends BasePresenter<SimpleUiInterface> implements  SimplePresenterImpl {

    public SimplePresenter(SimpleUiInterface mUiInterface) {
        super(mUiInterface);
    }

    @Override
    public void phoneLogin(String telephone, String password, String longitude, String latitude, String JpushId) {
        //添加到任务中
        RetrofitSourceFactory.createRetrofitApi(RetrofitApi.class).phonelogin(telephone,password,longitude,latitude,JpushId)
                .compose(getUiInterface().bindToLifecycleDestroy())//绑定生命周期免得泄露
                .subscribeOn(Schedulers.io())//切换生成端进程
                .observeOn(AndroidSchedulers.mainThread())//切换消费者进程
                .subscribe(new BaseObserver<BaseResponse<LocalUserInfo>>(getUiInterface()) {
                 @Override
                 protected void onSuccess(BaseResponse<LocalUserInfo> response) {
                     getUiInterface().loadDataSuccess(response.getDatas());//调用更新UI
                 }

                 @Override
                 public void onSubscribe(Disposable d) {
                     addSubscrietion(d);//添加到统一管理的容器中，当生命周期结束时候统一断开订阅
                 }

             });

    }


}
