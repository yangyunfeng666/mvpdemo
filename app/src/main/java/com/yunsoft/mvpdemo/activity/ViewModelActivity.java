package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.MyViewModel;
import com.yunsoft.mvpdemo.db.DataRepository;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.db.UserListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-12 17:12
 * Description:this is ViewModelActivity
 */


public class ViewModelActivity extends AppCompatActivity {

    private MyViewModel myViewModel;

    private UserListViewModel userListViewModel;

    private Button button;
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

}
