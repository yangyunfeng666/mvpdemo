package com.yunsoft.mvpdemo.http;

/**
 * Created by yyf on 2018-四月-11-11-52.
 * 请求地址 网址 请求url 地方
 */
public class HttpUrl {

    public final static String HTTPS = "https://";

    public final static String HTTP = "http://";

    // 网址域名或者服务器ip 我自己的服务器
    public final static String DOMAIN_URL = "47.52.135.9";

    //端口号
    public final static String DOMAIN_PORT = "8686";

    //接口类型分类 这里根据项目自己定义
    public final static String API = "/Minority/mobile/interface/";

    public final static String DOMAIN = DOMAIN_URL+":"+DOMAIN_PORT;

    //基础请求url
    public final static String BASEURL =  HTTP+DOMAIN+API;


}
