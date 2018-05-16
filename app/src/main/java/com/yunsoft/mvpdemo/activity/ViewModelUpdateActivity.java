package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.MyLiveData;
import com.yunsoft.mvpdemo.commponent.lifecycle.UserViewModel;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-15 11:08
 * Description:this is ViewModelUpdateActivity
 */

public class ViewModelUpdateActivity extends BaseMvpActivity {
    private Button button;

    @Override
    protected void onCreateBefore() {

    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_model_update);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLiveData.getInstance(getApplication()).setLocalUser( Math.random()+"dsdsdsdsdd");
            }
        });
    }

    @Override
    protected void initData() {

    }
}
