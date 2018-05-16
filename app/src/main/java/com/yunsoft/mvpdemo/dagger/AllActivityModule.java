package com.yunsoft.mvpdemo.dagger;

import com.yunsoft.mvpdemo.activity.AllActivityInjectorActivity;
import com.yunsoft.mvpdemo.activity.ViewModelActivity;

import dagger.Module;
import dagger.android.ContentProviderKey;
import dagger.android.ContributesAndroidInjector;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 14:19
 * Description:this is AllActivityModule
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AllActivityModule {
    //定义一个AllActivityInjectorActivity 的module 和AllActivityInjectorActivity 注入
    @ActivityScope
    @ContributesAndroidInjector(modules = AllActivityInjectorModule.class)
    abstract AllActivityInjectorActivity AllActivityInjectorActivity();
//
//    //定义一个AllActivityInjectorActivity 的module 和AllActivityInjectorActivity 注入
//    @ActivityScope
//    @ContributesAndroidInjector(modules = AllActivityInjectorModule.class)
//    abstract ViewModelActivity ViewModelActivity();
}
