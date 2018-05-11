package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kye.basemodule.glide.GlideUtils;
import com.kye.basemodule.view.MyCircleProgress;
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

    private Button click_btn;
    private MyCircleProgress myCircleProgress;
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_view);
        image = findViewById(R.id.image);
        myCircleProgress = findViewById(R.id.progress);
        GlideUtils.loadCircleWithBorderImageView(girl,image,1,getResources().getColor(R.color.blue));
        click_btn = findViewById(R.id.click_btn);
        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCircleProgress.setBorder_bg_color(getResources().getColor(R.color.black));
                myCircleProgress.setmProgress(50);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
