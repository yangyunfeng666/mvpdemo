package com.yunsoft.mvpdemo.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 15:02
 * Description:this is CarModule
 */

@Module
public class CarModule {
    private String vales;
    private String b;

    public CarModule(String vales,String b) {
        this.vales = vales;
        this.b = b;
    }

    @QualifierA
    @Provides
    Engine ProvideEngineA(){
        return  new Engine(vales);
    }

    @QualifierB
    @Provides
    Engine ProvideEngineB(){
        return  new Engine(b);
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierA { }
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierB { }
}



