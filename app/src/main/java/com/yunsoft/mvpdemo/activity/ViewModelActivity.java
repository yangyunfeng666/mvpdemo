package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.MyViewModel;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-12 17:12
 * Description:this is ViewModelActivity
 */


public class ViewModelActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        //注册这个myViewModel viewmodelStroe里面 通过 ViewModelProviders 与lifecycle 关联
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        //绑定当前生命周期和返回数据更新UI
        myViewModel.getList().observe(this,users -> {
            //update UI

        });

    }

}
