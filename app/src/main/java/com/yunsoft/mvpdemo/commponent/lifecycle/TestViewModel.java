package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataRepository;
import com.yunsoft.mvpdemo.data.source.UsersRespository;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 15:45
 * Description:this is TestViewModel
 */

public class TestViewModel extends AndroidViewModel {
    private DataRepository usersRespository;
    private Context mContext;
    private Application application;

    public TestViewModel(@NonNull Application application, DataRepository
            dataRepository) {
        super(application);
        this.mContext = application;
        this.usersRespository = dataRepository;
    }


}
