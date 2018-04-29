package com.yunsoft.mvpdemo.dagger;

import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 14:14
 * Description:this is BaseActivityComponent
 */

@Subcomponent(modules = {AndroidSupportInjectionModule.class, AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<BaseMvpActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseMvpActivity>{}
}
