package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.MyLiveData;
import com.yunsoft.mvpdemo.commponent.lifecycle.MyViewModel;
import com.yunsoft.mvpdemo.commponent.lifecycle.UserProfileViewModel;
import com.yunsoft.mvpdemo.commponent.lifecycle.UserViewModel;
import com.yunsoft.mvpdemo.db.DataRepository;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.db.UserListViewModel;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-12 17:12
 * Description:this is ViewModelActivity
 */


public class ViewModelActivity extends BaseMvpActivity {



    private Button button;

    private UserViewModel userViewModel;

    private TextView text;

    private Button update_btn;

    private UserProfileViewModel  userProfileViewModel;

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_model);

        text = findViewById(R.id.text);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(ViewModelActivity.this,ViewModelUpdateActivity.class));
            }
        });
        userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        userProfileViewModel.init("1");
        userProfileViewModel.getUserLiveData().observe(this, new Observer<LocalUser>() {
            @Override
            public void onChanged(@Nullable LocalUser localUser) {
                if(localUser!=null) {
                    text.setText(localUser.toString());
                }
            }
        });

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getLocalData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(!TextUtils.isEmpty(s)) {
                    text.setText(s);
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

}
