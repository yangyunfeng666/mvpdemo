package com.yunsoft.mvpdemo.dagger;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.activity.DaggerInjectActivityModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 11:38
 * Description:this is MyAppCommponent
 */

@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        DaggerInjectActivityModule.class,
        AllActivityModule.class

})
public interface MyAppCommponent {
    void  inject(MyApplication myApplication);
}
