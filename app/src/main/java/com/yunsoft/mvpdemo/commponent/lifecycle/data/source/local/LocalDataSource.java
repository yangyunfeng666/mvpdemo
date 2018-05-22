package com.yunsoft.mvpdemo.commponent.lifecycle.data.source.local;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.DataSource;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.local.LocalUserInfoDao;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 16:01
 * Description:this is remoteDataSource
 */

public class LocalDataSource  {

    private static volatile LocalDataSource mInstance;

    public static LocalDataSource getInstance(Application application){
        if(mInstance==null){
            synchronized (LocalDataSource.class){
                mInstance = new LocalDataSource();
                localUserInfoDao =  ((MyApplication)(application)).getDataBase().LocalUserInfoDao();
            }
        }
      return mInstance;
    }

    private static LocalUserInfoDao localUserInfoDao;

    public LiveData<LocalUserInfo> getLoginUser(String telephone, String password, String longitude, String latitude, String JpushId) {
       return localUserInfoDao.getLiveUserById(84);
    }

    public void insert(LocalUserInfo localUserInfo) {
         localUserInfoDao.insertUser(localUserInfo);
    }
}
