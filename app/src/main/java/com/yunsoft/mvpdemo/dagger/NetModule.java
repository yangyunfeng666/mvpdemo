package com.yunsoft.mvpdemo.dagger;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.FileNameMap;

import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-26 11:30
 * Description:this is NetModule
 */

@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }



    @Named("cache")
    @NetScope
    @Provides
    OkHttpClient ProvideOkHttpClient(Cache cache){
        OkHttpClient okHttpClient = new OkHttpClient( );
       return okHttpClient.newBuilder().cache(cache).build();
    }


    @NetScope
    @Provides
    Gson ProvideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return  gsonBuilder.create();
    }

    @NetScope
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    //自定义作用域
    @Documented
    @Scope
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface NetScope{

    }
}
