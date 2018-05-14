package com.yunsoft.mvpdemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 16:53
 * Description:this is DataRepository
 */

public class DataRepository {
    private static DataRepository mInstance;
    private  AppDatabase mAppDatabase;
    private MediatorLiveData<List<LocalUser>> mediatorLiveData;

    private DataRepository(AppDatabase appDatabase){
        this.mAppDatabase = appDatabase;
        mediatorLiveData = new MediatorLiveData<>();
        //本地数据变化 会通知
//        mediatorLiveData.addSource(appDatabase.LocalUserDao().quertAllLocalUser(), new Observer<List<LocalUser>>() {
//            @Override
//            public void onChanged(@Nullable List<LocalUser> localUsers) {
//                mediatorLiveData.postValue(localUsers);
//            }
//        });
        mediatorLiveData.addSource(appDatabase.LocalUserDao().quertAllLocalUser(),mediatorLiveData::postValue);
    }

    public void  setListUserData(List<LocalUser> userData){
        mediatorLiveData.postValue(userData);
    }

    public static DataRepository getInstance(AppDatabase appDatabase){
        if(mInstance==null){
            synchronized (DataRepository.class){
                if(mInstance==null){
                    mInstance = new DataRepository(appDatabase);
                }
            }
        }
        return mInstance;
    }

    public LiveData<List<LocalUser>> getLocalUserData(){
        return mediatorLiveData;
    }

}
