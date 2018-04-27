package com.yunsoft.mvpdemo.dagger;

import com.yunsoft.mvpdemo.activity.DaggerSubComponentActivity;

import dagger.Subcomponent;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 10:16
 * Description:this is ActivityComponent
 * 这个是我们要继承别的compoent的Component 是子component所以使用Subcomponent 标注他
 * 他完全不需要找到父component的详细资料
 */
@NetModule.NetScope
@Subcomponent(modules = NetModule.class)
public interface ActivitySubComponent {
    void inject(DaggerSubComponentActivity simpleActivity);
}
