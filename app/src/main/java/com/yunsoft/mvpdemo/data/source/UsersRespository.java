package com.yunsoft.mvpdemo.data.source;

import android.support.annotation.NonNull;

import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.local.UserLocalDataSource;
import com.yunsoft.mvpdemo.data.source.remote.UserRemoteDataSource;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:43
 * Description:this is UsersRespository
 */


public class UsersRespository implements UsersDataSource{

    private static UsersRespository INSTANCE = null;
    private UsersDataSource mUserLocalDataSource;
    private UsersDataSource mUserRemoteDataSource;

    private boolean isCached = false;

    public UsersRespository(UsersDataSource mUserLocalDataSource, UsersDataSource mUserRemoteDataSource) {
        this.mUserLocalDataSource = mUserLocalDataSource;
        this.mUserRemoteDataSource = mUserRemoteDataSource;
    }

    UsersRespository getInstance(UsersDataSource mUserLocalDataSource, UsersDataSource mUserRemoteDataSource){
        if(INSTANCE==null){
            INSTANCE = new UsersRespository(mUserLocalDataSource,mUserRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }


    @Override
    public void getUsers(String telephone, String password, String longitude, String latitude, String JpushId, GetUsersCallBack callBack) {

    }
}
