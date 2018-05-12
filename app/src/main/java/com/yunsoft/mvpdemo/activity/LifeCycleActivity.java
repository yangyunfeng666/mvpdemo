package com.yunsoft.mvpdemo.activity;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yunsoft.mvpdemo.commponent.lifecycle.MyObserver;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 12:13
 * Description:this is LifeCycleActivity
 */


public class LifeCycleActivity extends Activity implements LifecycleOwner  {

    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        Log.e("show","onCreate");
//        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
        getLifecycle().addObserver(new MyObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("show","onstart");
//        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }
}
