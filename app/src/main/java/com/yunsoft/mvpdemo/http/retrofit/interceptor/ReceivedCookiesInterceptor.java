package com.yunsoft.mvpdemo.http.retrofit.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.yunsoft.mvpdemo.MyApplication;

import org.reactivestreams.Publisher;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by yangyunfeng on 2017/9/5.
 * 保存服务器返回的cookie
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            //最近在学习RxJava,这里用了RxJava的相关API大家可以忽略,用自己逻辑实现即可.大家可以用别的方法保存cookie数据
            Flowable.just(originalResponse.headers("Set-Cookie"))
                    .flatMap(new Function<List<String>, Publisher<String>>() {
                        @Override
                        public Publisher<String> apply(List<String> strings) throws Exception {
                            return Flowable.fromIterable(strings);
                        }
                    })
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    }).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    cookieBuffer.append(s).append(";");
                }
            });
            //shareprefence存储
            SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences("cookie", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookieBuffer.toString());
            editor.commit();
        }

        return originalResponse;
    }
}

