package com.kye.basemodule.glide;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

/**
 * Created by yyf on 2018-04-17 10:17.
 * 简单的加载图片封装
 */

public class GlideUtils {

    /**
     *
     * @param url 请求url
     * @param imageView 图片控件
     */
    public static void load(Uri url, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).into(imageView);
    }

    /**
     *
     * @param url 请求url
     * @param imageView 图片控件
     */
    public static void load(String url, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).into(imageView);
    }
    /**
     *
     * @param url 请求url
     * @param requestOptions 参数配置
     * @param imageView 图片控件
     */
    public static void load(String url, RequestOptions requestOptions, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    /**
     *
     * @param url 请求url
     * @param requestOptions 参数配置
     * @param imageView 图片控件
     */
    public static void load(Uri url, RequestOptions requestOptions, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }



}
