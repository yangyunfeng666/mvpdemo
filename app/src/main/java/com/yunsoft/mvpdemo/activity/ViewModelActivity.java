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

    private MyViewModel myViewModel;


    private Button button;

    private UserViewModel userViewModel;

    private TextView text;

    private Button update_btn;

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_model);
        //注册这个myViewModel viewmodelStroe里面 通过 ViewModelProviders 与lifecycle 关联
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        //绑定当前生命周期和返回数据更新UI
        myViewModel.getList().observe(this,users -> {
            //update UI

        });
        text = findViewById(R.id.text);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(ViewModelActivity.this,ViewModelUpdateActivity.class));
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
        MyLiveData liveData = MyLiveData.getInstance(getApplication());
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(!TextUtils.isEmpty(s)) {
                    text.setText(s);
                }
            }
        });
        button = findViewById(R.id.button);
        //改变数据
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalUser localUser = new LocalUser();
                localUser.setUsername("张佳楠");
                localUser.setSex("男");
                localUser.setAge(12);
                localUser.setPublics("海南");
                List<LocalUser> localUsers = new ArrayList<>();
                localUsers.add(localUser);
                ((MyApplication)getApplication()).getRepository().setListUserData(localUsers);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
