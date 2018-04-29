package com.yunsoft.mvpdemo.dagger;

import android.app.Activity;

import com.yunsoft.mvpdemo.activity.DaggerInjectorActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 17:15
 * Description:this is DaggerInjectModule
 */

@Module(subcomponents = InjectActivityComponent.class)//添加subCompoent DaggerCarCommpone 他继承于 YourApplicationComponent
abstract class InjectActivitytModule {
    @Binds
    @IntoMap
    @ActivityKey(DaggerInjectorActivity.class)//绑定到的Activity
    abstract AndroidInjector.Factory<?extends Activity> bindDaggerActityInjectorFactory(InjectActivityComponent.Builder builder);
}

