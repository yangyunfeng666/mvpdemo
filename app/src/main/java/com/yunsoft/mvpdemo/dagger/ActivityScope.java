package com.yunsoft.mvpdemo.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-27 18:52
 * Description:this is ActivityScope
 * 定义一个命名区域
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
