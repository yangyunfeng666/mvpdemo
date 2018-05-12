package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 11:39
 * Description:this is MyObserver
 * 声明一个被观察者 实现LifecycleObserver
 */


public class MyObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.e("MyObserver","ON_START onDisConnectionListner");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onConnectionListener(){
        Log.e("MyObserver","ON_RESUME onDisConnectionListner");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDisConnectListener(){
        Log.e("MyObserver","ON_PAUSE onDisConnectionListner");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onstop(){
        Log.e("MyObserver","ON_DESTROY onDisConnectionListner");

    }

}
