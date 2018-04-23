package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BasePresenter;

/**
 * Created by yyf on 2018-04-11 17:03.
 * 定义presenter接口
 */

public interface SimplePresenterImpl  {

   /**
    * 手机登陆
    * @param telephone
    * @param password
    * @param longitude
    * @param latitude
    * @param JpushId
    */
   public void phoneLogin(String telephone, String password,String longitude,String latitude,String JpushId);


}
