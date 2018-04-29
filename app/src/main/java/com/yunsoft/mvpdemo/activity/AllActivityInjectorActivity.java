package com.yunsoft.mvpdemo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 17:14
 * Description:this is DaggerInjectorActivity
 * 这个是测试最基本的通过最新的ContributesAndroidInjector 注入
 */


public class AllActivityInjectorActivity extends BaseMvpActivity implements DaggerMvpContract.View {

    private TextView textView;
    private Button button;

    //这个注入从AllActivityInjectorModule提供的
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreateBefore() {
//        AndroidInjection.inject(this);//明确写入注入
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dagger_mvp);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(sharedPreferences.toString());
            }

        });
    }

    @Override
    protected void initData() {
        //注册依赖
    }

    @Override
    public void loadDataSuccess(LocalUserInfo userInfo) {
        textView.setText(userInfo.toString());
    }
}
