package com.yunsoft.mvpdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.yunsoft.mvpdemo.MyApplication;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-19 16:33
 * Description:this is LocalUserModelFactory
 */

public class LocalUserModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;

   public LocalUserModelFactory(Application Application) {
        mApplication = Application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LocalUserViewModel.class)) {
            return (T) new LocalUserViewModel(((MyApplication) mApplication).getDataBase().LocalUserDao(),((MyApplication) mApplication).getAppExecutors().diskIO());
        }

        throw new IllegalArgumentException("not instance ");

    }
}
