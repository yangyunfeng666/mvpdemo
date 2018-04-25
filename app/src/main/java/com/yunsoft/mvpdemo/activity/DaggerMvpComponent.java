package com.yunsoft.mvpdemo.activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-25 17:32
 * Description:this is DaggerMvpComponent
 */

@Singleton
@Component(modules = DaggerMvpModule.class)
public interface DaggerMvpComponent {
  void  inject(DaggerAndMvpActivity daggerAndMvpActivity);
}
