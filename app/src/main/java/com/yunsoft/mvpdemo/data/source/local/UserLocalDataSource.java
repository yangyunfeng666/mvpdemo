package com.yunsoft.mvpdemo.data.source.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.UsersDataSource;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:39
 * Description:this is UserLocalDataSource
 */


public class UserLocalDataSource  implements UsersDataSource,UserLocalDatabaseImpl{

    private static volatile UserLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;

    private LocalUserInfoDao mLocalUserDao;

    public UserLocalDataSource(AppExecutors mExecutors, LocalUserInfoDao mLocalUserDao) {
        this.mAppExecutors = mExecutors;
        this.mLocalUserDao = mLocalUserDao;
    }


    public static UserLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull LocalUserInfoDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (UserLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserLocalDataSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(String telephone, String password,String longitude,String latitude,String JpushId, GetUsersCallBack callBack) {
            checkNotNull(callBack);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    LocalUserInfo localUserInfo = mLocalUserDao.getUserById("59");
                    if(localUserInfo!=null){
                        KyeLogUtils.i("show","database get ");
                        callBack.onUserLocalLoad(localUserInfo);
                    }else {
                        callBack.onDataNotAvailable();
                    }
                }
            };
            mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void save(LocalUserInfo localUserInfo) {
        checkNotNull(localUserInfo);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mLocalUserDao.insertUser(localUserInfo);
                KyeLogUtils.i("show","save database");
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

}
