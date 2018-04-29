package com.yunsoft.mvpdemo.dagger;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 17:19
 * Description:this is DaggerInjectComponent
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;

import com.yunsoft.mvpdemo.activity.DaggerInjectorActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Subcomponent(modules = {AndroidSupportInjectionModule.class,InjectActivityComponent.SubModule.class})// 申明注解@Subcomponent 并继承自AndroidInjectror,并声明@Subcomponent.Builder:
public interface InjectActivityComponent extends AndroidInjector<DaggerInjectorActivity>{//继承AndroidInjector<YourActivity>
    //必须返回一个 builder 继承AndroidInjector.Builder<YourActivity>
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<DaggerInjectorActivity>{
    }
    @Module
    class SubModule{
        @Named("inject")
        @Provides
        SharedPreferences ProvideSharedPreferences(Context context){
          return   context.getSharedPreferences("share",Context.MODE_PRIVATE);
        }
    }
}

