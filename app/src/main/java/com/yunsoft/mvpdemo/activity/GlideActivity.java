package com.yunsoft.mvpdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kye.basemodule.glide.view.CircleProgressView;
import com.kye.basemodule.glide.GlideApp;
import com.kye.basemodule.glide.view.GlideImageView;
import com.kye.basemodule.glide.transformation.GlideCircleTransformation;
import com.kye.basemodule.shareperfence.Preference;
import com.yunsoft.mvpdemo.R;

/**
 * Created by yyf on 2018-04-14 17:17.
 */

public class GlideActivity extends Activity {

    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl.jpg";
    public static final String girl_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl_thumbnail.jpg";
    public static final String cat = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat.jpg";

    GlideImageView glideImageView ;
    CircleProgressView circleProgressView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        glideImageView = findViewById(R.id.glideImageView);
//        circleProgressView = findViewById(R.id.progressView1);//圆弧加载
        circleProgressView = findViewById(R.id.progressView1);//normal
//        circleProgressView = findViewById(R.id.progressView2);//
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        requestOptions.centerCrop();
        requestOptions.transform(new GlideCircleTransformation());
//        glideImageView.load(cat); 普通加载
//        glideImageView.loadCircleImage(cat,requestOptions); 带配置参数加载
//        glideImageView.loadCircleImage(cat,requestOptions, new OnGlideImageViewListener() {//设置进度加载
//            @Override
//            public void onProgress(int percent, boolean isDone, GlideException exception) {
//                Log.e("onProgress", percent + "" + isDone);
//                circleProgressView.setProgress(percent);
//                circleProgressView.setVisibility(!isDone ? View.VISIBLE : View.GONE);
//            }
//        });
        GlideApp.with(this).asBitmap().load(cat).centerCrop().into(glideImageView);
//        Glide.with(this).load(cat).apply(requestOptions).into(glideImageView);
//        glideImageView.loadLocalCircleImage(R.drawable.ic_launcher_background, requestOptions, new OnGlideImageViewListener() {
//            @Override
//            public void onProgress(int percent, boolean isDone, GlideException exception) {
//                Log.e("onProgress", percent + "" + isDone);
//                circleProgressView.setProgress(percent);
//                circleProgressView.setVisibility(!isDone ? View.VISIBLE : View.GONE);
//            }
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
