package com.yunsoft.mvpdemo.dagger;

import com.yunsoft.mvpdemo.activity.AllActivityInjectorActivity;

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
    @ContributesAndroidInjector(modules = AllActivityInjectorModule.class)
    abstract AllActivityInjectorActivity AllActivityInjectorActivity();
}
