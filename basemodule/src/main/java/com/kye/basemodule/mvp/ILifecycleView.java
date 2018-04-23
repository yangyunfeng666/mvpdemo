package com.kye.basemodule.mvp;


import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

public interface ILifecycleView {

    <T> LifecycleTransformer<T> bindToLifecycle();

    <T> LifecycleTransformer<T> bindToLifecycleDestroy();

    <T> LifecycleTransformer<T> bindToLifecycleEvent(Lifecycle.Event event);

    LifecycleProvider<Lifecycle.Event> getProvider();
}
