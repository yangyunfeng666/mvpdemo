package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.db.AppDatabase;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import java.util.List;

import dagger.Component;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 16:43
 * Description:this is RoomActivity
 */


public class RoomActivity extends BaseMvpActivity  {
    private Button add_btn;
    private Button update_btn;
    private Button delete_btn;
    private Button select_btn;
    private TextView show_txt;
    private String TAG = "RoomActivity";
    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_room);
        show_txt = findViewById(R.id.show_txt);
        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);
        select_btn = findViewById(R.id.select_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        LocalUser localUser = new LocalUser();
                        localUser.setAge(1);
                        localUser.setSex("男");
                        localUser.setUsername("小于");
                        ((MyApplication)getApplicationContext()).getDataBase().LocalUserDao().insert(localUser);
                        ((MyApplication)getApplicationContext()).getDataBase().init();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                            //添加完成
                        Log.e(TAG,"add data complete");
                        show_txt.setText("add data complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //添加错误
                        Log.e(TAG,"add data error");
                        show_txt.setText("add data error");
                    }
                });

            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        LocalUser localUser = new LocalUser();
                        localUser.setId(1);
                        localUser.setAge(1);
                        localUser.setSex("男");
                        localUser.setUsername("小于");
                        ((MyApplication)getApplicationContext()).getDataBase().LocalUserDao().update(localUser);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"update data onComplete");
                        show_txt.setText("update data onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"update data error");
                        show_txt.setText("update data error");
                    }
                });

            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        LocalUser localUser = new LocalUser();
                        localUser.setId(1);
                        localUser.setAge(1);
                        localUser.setSex("男");
                        localUser.setUsername("小于");
                        ((MyApplication)getApplicationContext()).getDataBase().LocalUserDao().delete(localUser);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"delete data onComplete");
                        show_txt.setText("delete data onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"delete data onError");
                        show_txt.setText("delete data onError");
                    }
                });
            }
        });
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里使用了
              ((MyApplication)getApplicationContext()).getDataBase().LocalUserDao().quertAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<LocalUser>>() {
                    @Override
                    public void accept(List<LocalUser> localUsers) throws Exception {
                        //这里在主线程调用了
                        LocalUser localUser1 = localUsers.get(localUsers.size()-1);
                        show_txt.setText("中的数据条数是："+localUsers.size()+"id:"+localUser1.getId()+"username:"+localUser1.getUsername()+"publics:"+localUser1.getPublics());
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {

    }


}
