package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BasePresenter;
import com.kye.basemodule.mvp.BaseView;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-25 17:24
 * Description:this is DaggerMvpContract
 * 定义mvp view 的接口 presenter 接口
 */


public class DaggerMvpContract {
    interface View extends BaseView {
        public void loadDataSuccess(LocalUserInfo userInfo);
    }

    interface Prsenter  {
        public void phoneLogin(String telephone, String password,String longitude,String latitude,String JpushId);
    }
}
