package com.yunsoft.mvpdemo.data.source.remote;

import com.kye.basemodule.mvp.BaseObserver;
import com.kye.basemodule.mvp.BasePresenter;
import com.kye.basemodule.mvp.BaseView;
import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.UsersDataSource;
import com.yunsoft.mvpdemo.http.RetrofitApi;
import com.yunsoft.mvpdemo.http.retrofit.RetrofitSourceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:40
 * Description:this is UserRemoteDataSource
 */


public class UserRemoteDataSource extends BasePresenter<BaseView> implements UsersDataSource {


 public UserRemoteDataSource(BaseView mUiInterface) {
  super(mUiInterface);
 }

 @Override
    public void getUsers(String telephone, String password,String longitude,String latitude,String JpushId, GetUsersCallBack callBack) {
     RetrofitSourceFactory.createRetrofitApi(RetrofitApi.class)
             .phonelogin(telephone,password,longitude,latitude,JpushId)
             .compose(getUiInterface().bindToLifecycleDestroy())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new BaseObserver<BaseResponse<LocalUserInfo>>(getUiInterface()) {
              @Override
              protected void onSuccess(BaseResponse<LocalUserInfo> response) {
               callBack.onUserLocalLoad(response.getDatas());
              }
             });
    }

}
