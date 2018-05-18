package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.VisibleForTesting;

import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataRepository;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 18:45
 * Description:this is ViewModelFactory
 * 自定义的ViewModelFactory
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final DataRepository mTasksRepository;

    public ViewModelFactory(Application mApplication, DataRepository mTasksRepository) {
        this.mApplication = mApplication;
        this.mTasksRepository = mTasksRepository;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,
                            Injection.provideTasksRepository(application));
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TestViewModel.class)) {
            //noinspection unchecked
            return (T) new TestViewModel(mApplication, mTasksRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }


}
