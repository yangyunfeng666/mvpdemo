package com.yunsoft.mvpdemo.http.retrofit;

import android.text.TextUtils;
import android.text.method.KeyListener;

import com.kye.basemodule.log.KyeLogUtils;
import com.kye.basemodule.network.RetrofitSource;
import com.yunsoft.mvpdemo.http.HttpUrl;
import com.yunsoft.mvpdemo.http.RetrofitApi;
import com.yunsoft.mvpdemo.http.retrofit.interceptor.ReceivedCookiesInterceptor;
import com.yunsoft.mvpdemo.http.retrofit.interceptor.RequstInterceptor;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yyf on 2018-04-11 15:42.
 * Retrofit数据源
 */

public class RetrofitSourceFactory {

    private static volatile RetrofitApi mSource;

    RetrofitSourceFactory(){

    }

    /**
     * 获取数据源。
     */
    public static RetrofitApi getInstance() {
        if (mSource == null) {
            synchronized (RetrofitSourceFactory.class) {
                if (mSource == null) {
                    createRetrofitApi();
                }
            }
        }
        return mSource;
    }

    /**
     * 默认的返回
     */
    public static void createRetrofitApi() {
        String baseUrlABS = schema(HttpUrl.BASEURL);
        RetrofitNetSource sou = new RetrofitNetSource(baseUrlABS);
        mSource = RetrofitSource.create(sou, RetrofitApi.class,new ReceivedCookiesInterceptor(),new RequstInterceptor());
    }

    /**
     *对象retrofit 接口对象
     */
    public static <T>T  createRetrofitApi(Class<T> tClass) {
        String baseUrlABS = schema(HttpUrl.BASEURL);
        RetrofitNetSource sou = new RetrofitNetSource(baseUrlABS);
       return RetrofitSource.create(sou, tClass,new ReceivedCookiesInterceptor(),new RequstInterceptor());
    }

    /**
     * 切换host
     * @param url host的url
     * @return
     */
    public static RetrofitApi createRetrofitApi(String url) {
        RetrofitNetSource sou = new RetrofitNetSource(url);
        mSource = RetrofitSource.create(sou, RetrofitApi.class,new ReceivedCookiesInterceptor(),new RequstInterceptor());
        return mSource;
    }



    public static String schema(String path) {
        return schema(path, HttpUrl.HTTP + HttpUrl.DOMAIN);
    }

    public static String schema(String path, String defaultSchema) {
        if (!TextUtils.isEmpty(path)) {
            if (path.startsWith("https://") || path.startsWith("http://")) {
                return path;
            } else {
                return defaultSchema + path;
            }
        }
        return "";
    }
}
