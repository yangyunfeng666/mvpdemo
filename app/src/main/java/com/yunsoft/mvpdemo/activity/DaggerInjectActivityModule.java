package com.yunsoft.mvpdemo.activity;

import android.app.Activity;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 11:31
 * Description:this is DaggerInjectActivityModule
 */

@Module(subcomponents={DaggerInjectActivitySubComponent.class})
public abstract class DaggerInjectActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(DaggerInjectorActivity.class)
    abstract AndroidInjector.Factory<?extends Activity> binderDaggerInjectActivity(DaggerInjectActivitySubComponent.Builder builder);
}
