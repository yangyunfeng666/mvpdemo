package com.yunsoft.mvpdemo.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.yunsoft.mvpdemo.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 17:54
 * Description:this is AppComponent
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application Application();//声明对子组件可见
    SharedPreferences SharedPreferences();//声明对子组件可见
}
