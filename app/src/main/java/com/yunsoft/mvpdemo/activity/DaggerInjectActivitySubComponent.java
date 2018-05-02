package com.yunsoft.mvpdemo.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.yunsoft.mvpdemo.pojo.Student;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 11:25
 * Description:this is SimpleActivitySubComponent
 */

@Subcomponent(modules = {AndroidInjectionModule.class,
        DaggerInjectActivitySubComponent.SubModule.class})
public interface DaggerInjectActivitySubComponent extends AndroidInjector<DaggerInjectorActivity> { //定义builder 针对具体的注入对象
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerInjectorActivity>{

    }
    @Module
    class SubModule{ //定义组件的module
        @Provides
        SharedPreferences sharedPreferences( DaggerInjectorActivity activity ){
           return  activity.getSharedPreferences("share",Context.MODE_PRIVATE);
        }
    }
}
