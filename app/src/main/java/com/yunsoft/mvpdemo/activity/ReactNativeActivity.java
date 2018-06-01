package com.yunsoft.mvpdemo.activity;

import com.facebook.react.ReactActivity;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-29 14:20
 * Description:this is ReactNativeActivity
 */

public class ReactNativeActivity extends ReactActivity implements DefaultHardwareBackBtnHandler {

    @javax.annotation.Nullable
    @Override
    protected String getMainComponentName() {
//        getReactNativeHost();
        return "test";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
