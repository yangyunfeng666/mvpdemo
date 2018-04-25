package com.yunsoft.mvpdemo.activity;

import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-25 17:34
 * Description:this is DaggerMvpModule
 * 定义提供DaggerMvpPresenter 的构造方法
 */

@Module
public class DaggerMvpModule {

    //需要view的实例传入才能构造
    private DaggerAndMvpActivity mvpActivity;

    public DaggerMvpModule(DaggerAndMvpActivity mvpActivity) {
        this.mvpActivity = mvpActivity;
    }
    @Singleton
    @Provides
    DaggerMvpPresenter ProvideDaggerMvpPresenter(){
        return  new DaggerMvpPresenter(mvpActivity);
    }
}
