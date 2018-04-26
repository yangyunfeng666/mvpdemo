package com.yunsoft.mvpdemo.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.kye.basemodule.network.RetrofitSource;
import com.yunsoft.mvpdemo.MyApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 17:47
 * Description:this is AppModule
 * 全局的module
 */

@Module
public class AppModule {

    private MyApplication mApplication;

    public AppModule(MyApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    Application ProvideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    Context ProvideContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    SharedPreferences ProvideSharedPreferences() {
        return mApplication.getSharedPreferences("shareperf",Context.MODE_PRIVATE);
    }


}
