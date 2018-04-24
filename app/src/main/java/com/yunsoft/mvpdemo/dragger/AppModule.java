package com.yunsoft.mvpdemo.dragger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 17:47
 * Description:this is AppModule
 * 全局的module
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application ProvideApplication(){
        return  mApplication;
    }

}
