package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BaseUiInterface;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:27
 * Description:this is MvpCacheContract
 */


public interface MvpCacheContract {

    interface View extends BaseUiInterface{
        void loadDataSucess(LocalUserInfo userInfo);
    }

    interface MyPresenter {
        void getPhoneLogin(String telephone, String password,String longitude,String latitude,String JpushId,boolean isCached);
    }

}
