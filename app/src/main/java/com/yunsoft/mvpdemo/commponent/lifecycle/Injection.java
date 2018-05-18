package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataRepository;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.source.local.LocalDataSource;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.source.remote.RemoteDataSource;
import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.db.AppDatabase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 18:46
 * Description:this is Injection
 */

public class Injection {

    public static DataRepository provideTasksRepository(@NonNull Application context) {
        checkNotNull(context);
        return DataRepository.getInstance(((MyApplication)context).getAppExecutors(),
                LocalDataSource.getInstance(context),RemoteDataSource.getInstance()
                        );
    }
}
