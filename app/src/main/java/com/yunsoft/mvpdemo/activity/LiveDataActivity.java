package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.NameViewModel;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 18:10
 * Description:this is LiveDataActivity
 * 这里我们得到一个NameViewModel 实现与viewModel，nameViewModel由ViewModelProviders
 * 注册创建，他会把viewmodel存放到ViewModelStore里面，然后在nameViewModel对象的observer绑定当前
 * 组件声明周期和注册观察者，如果数据更新而且在当前组件活跃生命周期内就更新通过观察者模式，通知观察者
 */


public class LiveDataActivity extends AppCompatActivity {

    private NameViewModel nameViewModel;
    private TextView text;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        text = findViewById(R.id.text);
        button = findViewById(R.id.onclick_btn);
        //得到viewmodel
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        //创建observer 当更新的时候更新UI
        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text.setText(s);
            }
        };
        //数据改变根据控件生命周期和数据观察者更新数据,如果 liftcycle 不在活跃状态，那么观察者不会收到更新
        //如果liftcycle object 销毁了，那么观察者会被自动移除监听
        nameViewModel.getmNameLiveData().observe(this,nameObserver);
//        or
//        nameViewModel.getmNameLiveData().observe(this,s -> {
//            text.setText(s);
//        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新数据
                nameViewModel.getmNameLiveData().setValue("hello world");
            }
        });

        LiveData<User> data = new LiveData<User>() {
            @Override
            protected void onActive() {
                super.onActive();
            }

            @Override
            protected void setValue(User value) {
                super.setValue(value);
            }

            @Override
            protected void onInactive() {
                super.onInactive();
            }
        };
        //Transformations值的转换
        LiveData<String> stringLiveData = Transformations.map(data,user->{
            return  user.getName()+":"+user.getComment();
        } );

        //MediatorLiveData
    }
}

