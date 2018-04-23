package com.kye.basemodule.mvp;

import android.util.Log;

import com.bumptech.glide.manager.LifecycleListener;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by yyf on 2018-04-11 14:45.
 * 基础的presenter
 */
public class BasePresenter<T extends ILifecycleView>  {

    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    private T mUiInterface;

    public BasePresenter(T mUiInterface) {
        this.mUiInterface = mUiInterface;
        if(mUiInterface!=null) {
            Observable.never()
                    .compose(mUiInterface.bindToLifecycleDestroy())
                    .doOnTerminate(new Action() {
                        @Override
                        public void run() throws Exception {
                            Log.e("doOnTerminate", "执行");
                            unSubscribeTasks();
                        }
                    })
                    .subscribe();
        }
    }


    /**
     * 移除没有完成的订阅任务
     */
    public void unSubscribeTasks() {
        mUiInterface = null;
        mCompositeSubscription.clear();
    }

    /**
     * 添加订阅任务
     *
     * @param subscription
     */
    public void addSubscrietion(Disposable subscription) {
        mCompositeSubscription.add(subscription);
    }

    /**
     * 获取View 对象
     *
     * @return
     */
    protected T getUiInterface() {
        if (mUiInterface == null) {
            throw new IllegalStateException("mUiInterface is not init");
        }
        return mUiInterface;
    }



}
