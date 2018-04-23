package com.kye.basemodule.glide.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.bumptech.glide.request.RequestOptions;
import com.kye.basemodule.glide.GlideImageLoader;
import com.kye.basemodule.glide.OnGlideImageViewListener;
import com.kye.basemodule.glide.OnProgressListener;
import com.kye.basemodule.glide.ShapeImageView;

/**
 * @author sunfusheng on 2017/11/10.
 */
public class GlideImageView extends ShapeImageView {

    private GlideImageLoader mImageLoader;

    public GlideImageView(Context context) {
        this(context, null);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mImageLoader = GlideImageLoader.create(this);
    }

    public GlideImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = GlideImageLoader.create(this);
        }
        return mImageLoader;
    }

    public String getImageUrl() {
        return getImageLoader().getImageUrl();
    }

    public RequestOptions requestOptions(int placeholderResId) {
        return getImageLoader().requestOptions(placeholderResId);
    }

    public RequestOptions circleRequestOptions(int placeholderResId) {
        return getImageLoader().circleRequestOptions(placeholderResId);
    }

    public GlideImageView load(int resId, RequestOptions options) {
        getImageLoader().load(resId, options);
        return this;
    }
    /**
     *
     * @param uri 请求url
     * @param options 请求配置
     * @return
     */
    public GlideImageView load(Uri uri, RequestOptions options) {
        getImageLoader().load(uri, options);
        return this;
    }

    /**
     *
     * @param url 请求url
     * @param options 请求配置
     * @return
     */
    public GlideImageView load(String url, RequestOptions options) {
        getImageLoader().load(url, options);
        return this;
    }

    /**
     *
     * @param url 请求url
     * @param options 请求配置
     * @param listener 加载监听
     * @return
     */
    public GlideImageView load(String url, RequestOptions options,OnGlideImageViewListener listener) {
        load(url, options).setOnGlideImageViewListener(listener);
        return this;
    }
    /**
     *
     * @param uri 请求url
     * @param options 请求配置
     * @param listener 加载监听
     * @return
     */
    public GlideImageView load(Uri uri, RequestOptions options,OnGlideImageViewListener listener) {
        load(uri, options).setOnGlideImageViewListener(listener);
        return this;
    }

    public GlideImageView load(String url, int placeholderResId) {
        getImageLoader().loadImage(url, placeholderResId);
        return this;
    }

    public GlideImageView loadLocalImage(@DrawableRes int resId, int placeholderResId) {
        getImageLoader().loadLocalImage(resId, placeholderResId);
        return this;
    }

    public GlideImageView loadLocalImage(String localPath, int placeholderResId) {
        getImageLoader().loadLocalImage(localPath, placeholderResId);
        return this;
    }

    /**
     * 请求圆弧图片
     * @param url
     * @param placeholderResId hold 图片
     * @return
     */
    public GlideImageView loadCircleImage(String url, int placeholderResId) {
        setCircle(true);
        getImageLoader().loadCircleImage(url, placeholderResId);
        return this;
    }

    /**
     * 加载圆弧图片
     * @param url 请求url
     * @param requestOptions 请求配置
     * @return
     */
    public GlideImageView loadCircleImage(String url,RequestOptions requestOptions) {
        setCircle(true);
        getImageLoader().loadCircleImage(url,requestOptions);
        return this;
    }

    /**
     * 请求圆弧图片
     * @param url
     * @return
     */
    public GlideImageView loadCircleImage(String url) {
        setCircle(true);
        getImageLoader().loadCircleImage(url);
        return this;
    }

    /**
     * 请求圆弧图片
     * @param url
     * @param onGlideImageViewListener
     * @return
     */
    public GlideImageView loadCircleImage(String url,OnGlideImageViewListener onGlideImageViewListener) {
       loadCircleImage(url).setOnGlideImageViewListener(onGlideImageViewListener);
        return this;
    }

    /**
     * 加载圆弧图片
     * @param url url
     * @param requestOptions 图片配置
     * @param onGlideImageViewListener 监听
     * @return
     */
    public GlideImageView loadCircleImage(String url,RequestOptions requestOptions,OnGlideImageViewListener onGlideImageViewListener) {
        loadCircleImage(url,requestOptions).setOnGlideImageViewListener(onGlideImageViewListener);
        return this;
    }

    public GlideImageView loadLocalCircleImage(int resId, int placeholderResId) {
        setCircle(true);
        getImageLoader().loadLocalCircleImage(resId, placeholderResId);
        return this;
    }

    public GlideImageView loadLocalCircleImage(int resId) {
       return loadLocalCircleImage(resId,new RequestOptions());
    }

    /**
     * 加载本地图片并且加上监听
     * @param resId 资源文件
     * @param requestOptions 请求参数
     * @param onGlideImageViewListener 监听
     * @return
     */
    public GlideImageView loadLocalCircleImage(int resId,RequestOptions requestOptions,OnGlideImageViewListener onGlideImageViewListener) {
      return loadLocalCircleImage(resId,requestOptions).setOnGlideImageViewListener(onGlideImageViewListener);
    }

    public GlideImageView loadLocalCircleImage(int resId,RequestOptions requestOptions) {
        setCircle(true);
        getImageLoader().loadLocalCircleImage(resId,requestOptions);
        return this;
    }

    /**
     * 加载本地资源
     * @param localPath 资源路径
     * @param placeholderResId hold资源
     * @return
     */
    public GlideImageView loadLocalCircleImage(String localPath, int placeholderResId) {
        setCircle(true);
        getImageLoader().loadLocalCircleImage(localPath, placeholderResId);
        return this;
    }


    /**
     * 设置加载监听
     * @param listener
     * @return
     */
    private GlideImageView setOnGlideImageViewListener(OnGlideImageViewListener listener) {
        getImageLoader().setOnGlideImageViewListener( listener);
        return this;
    }

    /**
     *
     * @param url 请求url
     * @param listener 设置加载的监听
     * @return
     */
    public GlideImageView load(String url,OnGlideImageViewListener listener) {
            load(url).setOnGlideImageViewListener(listener);
        return this;
    }

    /**
     *
     * @param uri 请求url
     * @param listener 设置加载的监听
     * @return
     */
    public GlideImageView load(Uri uri,OnGlideImageViewListener listener) {
        load(uri).setOnGlideImageViewListener(listener);
        return this;
    }

    /**
     *
     * @param url 请求url
     * @return
     */
    public GlideImageView load(String url) {
        getImageLoader().load(url);
        return this;
    }

    /**
     *
     * @param uri 请求uri
     * @return
     */
    public GlideImageView load(Uri uri) {
        getImageLoader().load(uri);
        return this;
    }


    private GlideImageView listener(OnProgressListener listener) {
        getImageLoader().setOnProgressListener( listener);
        return this;
    }
}
