package com.yunsoft.mvpdemo.dragger;

import android.app.Activity;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 17:54
 * Description:this is AppComponent
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void  inject(Activity activity);
}
