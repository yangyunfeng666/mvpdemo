package com.yunsoft.mvpdemo.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 10:16
 * Description:this is ActivityComponent
 */
@Singleton
@Component(modules = AppModule.class)
public interface ActivityComponent {
    //xxxxSubComponent addSub xxxxMoudle 这是把subComponent添加到里面去
    //添加子subcomponent 的一个方法  这个2个compoent就内聚了
    ActivitySubComponent addSub(NetModule appModule);
}
