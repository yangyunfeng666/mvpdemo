package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.kye.basemodule.glide.GlideUtils;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-2 17:11
 * Description:this is MyViewActivity
 */


public class MyViewActivity extends BaseMvpActivity {
    private ImageView image;
    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl.jpg";

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_view);
        image = findViewById(R.id.image);
        GlideUtils.loadCircleWithBorderImageView(girl,image,1,getResources().getColor(R.color.blue));
    }

    @Override
    protected void initData() {

    }
}
