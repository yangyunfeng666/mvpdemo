package com.yunsoft.mvpdemo.dagger;

import android.app.Activity;

import com.yunsoft.mvpdemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 17:54
 * Description:this is AppComponent
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void  inject(Activity activity);

}
