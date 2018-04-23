package com.yunsoft.mvpdemo.http.retrofit;

import com.kye.basemodule.network.RetrofitImpl;
import com.yunsoft.mvpdemo.http.retrofit.interceptor.ReceivedCookiesInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by yyf on 2018-04-11 15:08.
 * retrofit具体实现类
 */

public class RetrofitNetSource extends RetrofitImpl {

    //连接超时时间
    private static final int CONNECT_TIME_OUT = 10;
    //写超时时间
    private static final int WRITE_TIME_OUT = 10;
    //读超时时间
    private static final int READ_TIME_OUT = 10;
    //网站或者IP地址
    private  String HOST_ADDRESS;

    public RetrofitNetSource(String HOST_ADDRESS) {
        this.HOST_ADDRESS = HOST_ADDRESS;
    }

    @Override
    public String callHostAddress() {
        return this.HOST_ADDRESS;
    }


    @Override
    public Call.Factory callClient(Interceptor... args) {
        return getClient(args);
    }

    private Call.Factory getClient(Interceptor ...args) {
        OkHttpClient okHttpClient = RetrofitNetSource.normal();
        OkHttpClient.Builder clientBuilder = okHttpClient.newBuilder();

        SSLContext sslctxt = null;
        try {
            sslctxt = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        }};

        try {
            sslctxt.init(null, trustAllCerts, new java.security.SecureRandom());
            clientBuilder.sslSocketFactory(sslctxt.getSocketFactory()).hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    //验证域名忽略所有
                    return true;
                }
            });
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        for (Interceptor interceptor:args){//添加拦截器
            clientBuilder.addInterceptor(interceptor);
        }

        return clientBuilder.build();
    }

    public static OkHttpClient normal() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();
    }


}
