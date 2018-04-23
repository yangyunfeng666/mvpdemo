package com.yunsoft.mvpdemo.http.retrofit.interceptor;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;


import com.kye.basemodule.BuildConfig;
import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by qgh on 2015-四月-01.
 */

public class RequstInterceptor implements Interceptor {
    String version = "";
    String device = Build.MODEL;
    String device_id = "";

    public RequstInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        String token = "";
        try {
            //存在可能取不到Token的情况，所以只在有信息的时候调用getToken，否则传空。
            token = "";
        } catch (Exception e) {
            token = "";
        }
        HttpUrl httpUrl = request
                .url()
                .newBuilder()
                .addQueryParameter("device", Build.MODEL)//添加公共参数
                .addQueryParameter("token", token)
                .build();


        //存放基本信息 cookie
        StringBuffer buffers = new StringBuffer();
        buffers.append("device=" + device
                + ";device_id=" + device_id
                + ";version=" + version
                + ";lang=zh"
                + ";application=" + BuildConfig.APPLICATION_ID.replaceAll("\\.", "")
                + ";platform=android"
                + ";token=" + TextUtils.isEmpty(token) + ";"
        );
        String savecookie = MyApplication.getInstance().getSharedPreferences("cookie", Context.MODE_PRIVATE).getString("cookie", "");
        buffers.append(savecookie);
        request = request
                .newBuilder()
                .url(httpUrl)
                .addHeader("Cookie", buffers.toString())
                .build();

        boolean logHeaders = true;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        String method = request.method();
        StringBuilder sb = new StringBuilder();
        StringBuffer heads = new StringBuffer();
        if ("POST".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
            }
        }

        if (logHeaders) {
            if (hasRequestBody) {
                if (requestBody.contentType() != null) {
//                    KyeLogUtils.i("Content-Type: " + requestBody.contentType()+"");
                }
                if (requestBody.contentLength() != -1) {
//                    KyeLogUtils.i("Content-Length: "+requestBody.contentLength()+"");
                }
            }

            Headers headers = request.headers();

            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    heads.append(name + ":" + headers.value(i) + "");
                }
            }
        }

        long t1 = System.nanoTime();//请求发起的时间

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        if ("POST".equals(request.method())) {
            KyeLogUtils.d(String.format("请求url：%s %n请求类型：%s %npost requstParams：%s %n请求头：%s %n返回json：%n%s %n请求时间： %.1fms %n%s",
                    response.request().url(),
                    "POST".equals(request.method()) ? "POST" : "GET",
                    sb.toString(),
                    heads.toString(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
        } else {
            KyeLogUtils.d(String.format("请求url：%s %n请求类型：%s %n请求头：%s %n返回json：%n%s %n请求时间： %.1fms %n%s",
                    response.request().url(),
                    "POST".equals(request.method()) ? "POST" : "GET",
                    heads.toString(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
        }
        return response;
    }
}
