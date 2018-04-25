package com.yunsoft.mvpdemo.dagger;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.kye.basemodule.network.RetrofitSource;

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

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application ProvideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Named("no_cached")
    @Provides
    OkHttpClient ProvideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor apikey = chain -> chain.proceed(chain.request().newBuilder()
                .addHeader("apikey", "aaa").build());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.MILLISECONDS)
                .connectTimeout(30, TimeUnit.MILLISECONDS)
                .addInterceptor(apikey)
                .addInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }


}
