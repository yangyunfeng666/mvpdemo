package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-2 17:11
 * Description:this is MyViewActivity
 */


public class MyViewActivity extends BaseMvpActivity {
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_view);
    }

    @Override
    protected void initData() {

    }
}
