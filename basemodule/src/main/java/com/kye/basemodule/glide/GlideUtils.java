package com.kye.basemodule.glide;

import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kye.basemodule.glide.transformation.GlideCircleTransformation;
import com.kye.basemodule.glide.transformation.GlideCircleWithBorderTransformation;

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

    /**
     * 加载圆形图像
     * @param url
     * @param imageView
     */
    public static void loadCircleImageView(String url,ImageView imageView){
        GlideApp.with(imageView.getContext()).asBitmap().load(url).centerCrop().transform(new GlideCircleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
    }

    /**
     * 加载圆形图像
     * @param url
     * @param imageView
     * @param placeHolder
     */
    public static void loadCircleImageView(String url,ImageView imageView,@DrawableRes int placeHolder){
        GlideApp.with(imageView.getContext()).asBitmap().centerCrop().placeholder(placeHolder).transform(new GlideCircleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
    }

    /**
     * 加载圆形图像而且也边框
     * @param url
     * @param imageView
     */
    public static void loadCircleWithBorderImageView(String url, ImageView imageView,  int borderWith,  int borderColor){
        GlideApp.with(imageView.getContext()).asBitmap().load(url)
                .centerCrop()
                .transform(new GlideCircleWithBorderTransformation(borderWith,borderColor))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }
}
