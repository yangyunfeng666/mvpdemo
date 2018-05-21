package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kye.basemodule.network.base.BaseResponse;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataRepository;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
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
    private LiveData<Resource<LocalUserInfo>> liveData;

    public TestViewModel(@NonNull Application application, DataRepository
            dataRepository) {
        super(application);
        this.mContext = application;
        this.usersRespository = dataRepository;
    }


    public LiveData<Resource<LocalUserInfo>> loadData() {
        if (liveData == null) {
            liveData = usersRespository.getLoginUser("1326579778", "ab244795339868d6e9d35ed7e7de7e3b", "104.22", "12.2", "31231213233");
        }
        return liveData;
    }

}
