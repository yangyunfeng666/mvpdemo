package com.yunsoft.mvpdemo.dagger;


import android.app.Activity;

import com.yunsoft.mvpdemo.activity.SimpleActivity;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-26 12:03
 * Description:this is NetCommponent
 */
@NetModule.NetScope//与父组件不同的作用域
@Component(modules = NetModule.class,dependencies = AppComponent.class) //依赖 AppComponent 从AppComponent那里得到Context
public interface NetCommponent {
 void inject(SimpleActivity Activity);//这里不能写父类比如 Activity 只能写注入的那个类
}
