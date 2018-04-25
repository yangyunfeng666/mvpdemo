package com.yunsoft.mvpdemo.dagger;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 14:56
 * Description:this is CarCommpone
 */

@Component(modules = {CarModule.class})
public interface CarCommpone {
    void inject(Car car);
}
