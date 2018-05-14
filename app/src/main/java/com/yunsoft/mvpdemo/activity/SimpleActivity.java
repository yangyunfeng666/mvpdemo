package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.db.UserDao;
import com.yunsoft.mvpdemo.db.UserListViewModel;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.Date;
import java.util.List;

/**
 * Created by yyf on 2018-04-11 16:53.
 */

public class SimpleActivity extends BaseMvpActivity implements SimpleView {

    private Button glide_btn;
    private Button green_view;
    private Button brid_btn;
    private Button jsbrid_btn;
    private Button cache_btn;
    private Button dragger_btn;
    private Button dragger_mvp_btn;
    private Button dragger_dependencise_btn;//component依赖
    private Button dragger_subcomponent_btn;//subcomponent
    private Button dragger_inject_btn;// inject 统一注入
    private Button dragger_base_inject_btn;// 在baseAllModule里面注入
    private Button my_view;// 自定义view 例子
    private Button my_listview;// 自定义listview 例子
    private Button room_view;// room数据库操作 例子
    private Button lifecycle_view;// lifecycle  例子
    private Button livedata_view;// livedata  例子
    private Button viewmodel_view;// viewmodel  例子
    private TextView show_txt;
    private SimplePresenter presenter;
    private Button load_btn;
    @Override
    protected void onCreateBefore() {
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_simple);

        cache_btn = findViewById(R.id.cache_btn);
        load_btn = findViewById(R.id.load_btn);
        glide_btn = findViewById(R.id.glide_btn);
        brid_btn = findViewById(R.id.brid_btn);
        jsbrid_btn = findViewById(R.id.jsbrid_btn);
        dragger_btn = findViewById(R.id.dragger_btn);
        my_listview = findViewById(R.id.my_listview);
        room_view = findViewById(R.id.room_view);
        dragger_mvp_btn = findViewById(R.id.dragger_mvp_btn);
        green_view = findViewById(R.id.green_view);
        lifecycle_view = findViewById(R.id.lifecycle_view);
        my_view = findViewById(R.id.my_view);
        livedata_view = findViewById(R.id.livedata_view);
        dragger_dependencise_btn = findViewById(R.id.dragger_dependencise_btn);
        dragger_subcomponent_btn = findViewById(R.id.dragger_subcomponent_btn);
        dragger_base_inject_btn = findViewById(R.id.dragger_base_inject_btn);
        dragger_inject_btn = findViewById(R.id.dragger_inject_btn);
        viewmodel_view = findViewById(R.id.viewmodel_view);
        show_txt = findViewById(R.id.show_txt);
       //presenter 对象声明
        presenter = new SimplePresenter(this);
        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用presenter 方法调用数据
                presenter.phoneLogin("13265797978","ab244795339868d6e9d35ed7e7de7e3b","104.22","12.2","31231213233");
            }
        });
        glide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,GlideActivity.class);
                startActivity(intent);
            }
        });
        brid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,HyBridActivity.class);
                startActivity(intent);
            }
        });
        jsbrid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,JsBridActivity.class);
                startActivity(intent);
            }
        });

        cache_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,MvpCacheActivity.class);
                startActivity(intent);
            }
        });
        dragger_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,DaggerActivity.class);
                startActivity(intent);
            }
        });
        dragger_mvp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,DaggerAndMvpActivity.class);
                startActivity(intent);
            }
        });
        dragger_dependencise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,DaggerDependenciesComponentActivity.class);
                startActivity(intent);
            }
        });
        dragger_subcomponent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,DaggerSubComponentActivity.class);
                startActivity(intent);
            }
        });
        dragger_inject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,DaggerInjectorActivity.class);
                startActivity(intent);
            }
        });
        dragger_base_inject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,AllActivityInjectorActivity.class);
                startActivity(intent);
            }
        });
        my_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,MyViewActivity.class);
                startActivity(intent);
            }
        });
        my_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,MyListViewActivity.class);
                startActivity(intent);
            }
        });
        room_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,RoomActivity.class);
                startActivity(intent);
            }
        });
        green_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,GreenDaoActivity.class);
                startActivity(intent);
            }
        });

        lifecycle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,LifeCycleActivity.class);
                startActivity(intent);
            }
        });
        livedata_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,LiveDataActivity.class);
                startActivity(intent);
            }
        });
        viewmodel_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleActivity.this,ViewModelActivity.class);
                startActivity(intent);
            }
        });


        UserListViewModel userListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);

        userListViewModel.getUserListData().observe(this, new Observer<List<LocalUser>>() {
            @Override
            public void onChanged(@Nullable List<LocalUser> localUsers) {
                if(localUsers!=null){
                    for (LocalUser u: localUsers
                            ) {
                        KyeLogUtils.e(u.toString());

                    }
                }
            }
        });
    }

    //view 更新
    @Override
    public void loadDataSuccess(LocalUserInfo data) {
        show_txt.setText(data.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }



}
