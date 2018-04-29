package com.yunsoft.mvpdemo.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.yunsoft.mvpdemo.activity.AllActivityInjectorActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-29 14:21
 * Description:this is AllActivityInjectorModule
 */

@Module
public class AllActivityInjectorModule {
    @Provides
    SharedPreferences ProvidesharedPreferences(AllActivityInjectorActivity activity){
      return   activity.getSharedPreferences("share", Context.MODE_PRIVATE);
    }
}
