package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.db.UserDao;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.Date;
import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 18:05
 * Description:this is GreenDaoActivity
 */

public class GreenDaoActivity extends BaseMvpActivity {

    private TextView text;
    private Button add_btn;
    private Button look_btn;
    private Button update_btn;
    private Button select_btn;
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_greendao);
        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        look_btn = findViewById(R.id.look_btn);
        select_btn = findViewById(R.id.select_btn);
        text = findViewById(R.id.show_txt);

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



    }

    @Override
    protected void initData() {

    }
}
