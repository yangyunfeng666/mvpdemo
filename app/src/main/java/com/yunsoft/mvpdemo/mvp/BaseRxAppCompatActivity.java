package com.yunsoft.mvpdemo.mvp;

import android.arch.lifecycle.Lifecycle;
import android.support.v7.app.AppCompatActivity;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-18 19:28
 * Description:this is BaseRxAppCompatActivity
 */


public class BaseRxAppCompatActivity extends AppCompatActivity {


    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);

    public <T> LifecycleTransformer<T> bindToLifecycleDestroy() {
        return provider.bindUntilEvent(Lifecycle.Event.ON_DESTROY);
    }

    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return provider.bindToLifecycle();
    }

    public <T> LifecycleTransformer<T> bindToLifecycleEvent(Lifecycle.Event event) {
        return provider.bindUntilEvent(event);
    }

    public LifecycleProvider<Lifecycle.Event> getProvider() {
        return provider;
    }


}
