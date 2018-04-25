package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.dagger.CarModule;
import com.yunsoft.mvpdemo.dagger.Engine;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import javax.inject.Inject;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 14:06
 * Description:this is DraggerActivity
 */


public class DaggerAndMvpActivity extends BaseMvpActivity implements DaggerMvpContract.View {

    private TextView textView;
    private Button button;

    //依赖标识
    @Inject
    DaggerMvpPresenter presenter;

    @Override
    protected void onCreateBefore() {
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dagger_mvp);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.phoneLogin("13265797978","ab244795339868d6e9d35ed7e7de7e3b","104.22","12.2","31231213233");

            }
        });
    }

    @Override
    protected void initData() {
        //注册依赖
        DaggerDaggerMvpComponent.builder().daggerMvpModule(new DaggerMvpModule(this)).build().inject(this);
    }

    @Override
    public void loadDataSuccess(LocalUserInfo userInfo) {
        textView.setText(userInfo.toString());
    }
}
