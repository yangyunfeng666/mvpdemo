package com.yunsoft.mvpdemo.dagger;

import javax.inject.Inject;
import javax.inject.Qualifier;

import dagger.Component;
import dagger.Module;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 14:15
 * Description:this is Car
 */


public class Car {
   @CarModule.QualifierA
   @Inject //表示依赖
    Engine engine;

    Car(){
//        DaggerCarCommpone.builder().carModule(new CarModule("hellp")).build().inject(this);
    }
}
