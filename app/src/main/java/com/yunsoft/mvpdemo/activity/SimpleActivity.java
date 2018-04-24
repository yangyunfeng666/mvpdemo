package com.yunsoft.mvpdemo.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.db.UserDao;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yyf on 2018-04-11 16:53.
 */

public class SimpleActivity extends BaseMvpActivity implements  SimpleUiInterface {

    private Button load_btn;
    private Button add_btn;
    private Button look_btn;
    private Button update_btn;
    private Button select_btn;
    private Button glide_btn;
    private Button brid_btn;
    private Button jsbrid_btn;
    private Button cache_btn;
    private Button dragger_btn;
    private TextView show_txt;
    private TextView text;
    private SimplePresenter presenter;
    @Inject
    Application mApplication;
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_simple);
        load_btn = findViewById(R.id.load_btn);
        cache_btn = findViewById(R.id.cache_btn);
        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        look_btn = findViewById(R.id.look_btn);
        select_btn = findViewById(R.id.select_btn);
        glide_btn = findViewById(R.id.glide_btn);
        brid_btn = findViewById(R.id.brid_btn);
        jsbrid_btn = findViewById(R.id.jsbrid_btn);
        dragger_btn = findViewById(R.id.dragger_btn);
        show_txt = findViewById(R.id.show_txt);
        text = findViewById(R.id.text);
        //从注册组件
        ((MyApplication)getApplication()).getAppComponent().inject(this);
        //presenter 对象声明
        presenter = new SimplePresenter(this);
        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用presenter 方法调用数据
                presenter.phoneLogin("13265797978","ab244795339868d6e9d35ed7e7de7e3b","104.22","12.2","31231213233");
            }
        });

//        KyeLogUtils.e("出错了");


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setComment("ddddd"+System.currentTimeMillis());
                user.setDate(new Date());
                user.setText("dasdasd"+System.currentTimeMillis());
              long a =  MyApplication.getInstance().getDaoSession().getUserDao().insert(user);
              KyeLogUtils.e("insertData:"+a,"result long a:"+a);
            }
        });

        look_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> data = MyApplication.getInstance().getDaoSession().getUserDao().loadAll();
                StringBuffer stringBuffer =new StringBuffer();
                for(User d:data){
                   stringBuffer.append(d.toString());
                }
                text.setText(stringBuffer);
            }
        });

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是判断 id>=3的数据读取出来 当然你也可以其他的查询
                List<User> data = MyApplication.getInstance().getDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Id.ge(3)).list();
                StringBuffer stringBuffer =new StringBuffer();
                for(User d:data){
                    stringBuffer.append(d.toString());
                }
                text.setText(stringBuffer);
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setComment("修改的"+System.currentTimeMillis());
                user.setDate(new Date());
                user.setText("dasdasd"+System.currentTimeMillis());
                user.setId(1l);//设置主键
                MyApplication.getInstance().getDaoSession().getUserDao().update(user);
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setComment("修改的"+System.currentTimeMillis());
                user.setDate(new Date());
                user.setText("dasdasd"+System.currentTimeMillis());
                user.setId(1l);//设置主键
                MyApplication.getInstance().getDaoSession().getUserDao().update(user);
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
                Intent intent = new Intent(SimpleActivity.this,DraggerActivity.class);
                startActivity(intent);
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
        //取消请求任务
        presenter.unSubscribeTasks();
    }

    @Override
    protected void initData() {

    }

}
