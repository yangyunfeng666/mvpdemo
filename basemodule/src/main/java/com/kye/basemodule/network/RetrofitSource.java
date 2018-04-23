package com.kye.basemodule.network;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import retrofit2.Retrofit;

/**
 * Created by yyf on 2018-04-11 14:57.
 */

public class RetrofitSource {

    RetrofitSource(){
    }

    /**
     * 返回 Retrofit对象
     * @param impl
     * @return
     */
    private Retrofit createRetrofit(RetrofitImpl impl, Interceptor ...args) {
        return new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(impl.callHostAddress()))
                .addConverterFactory(impl.callConverterFactory())
                .addCallAdapterFactory(impl.callAdapterFactory())
                .callFactory(impl.callClient(args))
                .build();
    }

    /**
     * @return 网络接口类
     */
    public static <T> T create(RetrofitImpl impl, Class<T> t,Interceptor ... args) {
        return new RetrofitSource().createIMML(impl, t, args);
    }

    /**
     * @return 网络接口类
     */
    private <T> T createIMML(RetrofitImpl impl, Class<T> t,Interceptor ...args) {
        return createRetrofit(impl,args).create(t);
    }


}
