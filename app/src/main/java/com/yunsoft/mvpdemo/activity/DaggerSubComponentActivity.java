package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.dagger.NetModule;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 11:05
 * Description:this is DaggerSubComponentActivity
 */


public class DaggerSubComponentActivity extends BaseMvpActivity {
    private TextView textView ;
    private Button button;
    @Named("cache")//必须要和构造提供的标注一样 如果是qulifter 也是一样的
    @Inject
    OkHttpClient mOkHttpClient ;
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
    setContentView(R.layout.activity_dagger_sub_activity);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        ((MyApplication)getApplication()).getActivityComponent().ActivitySubComponent(new NetModule(com.yunsoft.mvpdemo.http.HttpUrl.BASEURL)).inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            textView.setText("okhttp:"+mOkHttpClient.toString());
            }
        });
    }

    @Override
    protected void initData() {

    }
}
