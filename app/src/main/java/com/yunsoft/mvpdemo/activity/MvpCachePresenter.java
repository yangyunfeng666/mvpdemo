package com.yunsoft.mvpdemo.activity;

import android.content.Context;
import android.util.Log;

import com.kye.basemodule.log.KyeLogUtils;
import com.kye.basemodule.mvp.BasePresenter;
import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.UsersDataSource;
import com.yunsoft.mvpdemo.data.source.local.LocalDatabase;
import com.yunsoft.mvpdemo.data.source.local.UserLocalDataSource;
import com.yunsoft.mvpdemo.data.source.remote.UserRemoteDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:32
 * Description:this is MvpCachePresenter
 */


public class MvpCachePresenter extends BasePresenter<MvpCacheContract.View> implements MvpCacheContract.MyPresenter {

    private UserLocalDataSource usersLocalDataSource;
    private UsersDataSource usersRemoteDataSource;

    private Map<String, LocalUserInfo> mCachedTasks;

    public MvpCachePresenter(MvpCacheContract.View mUiInterface) {
        super(mUiInterface);
        LocalDatabase database = LocalDatabase.getInstance((Context) mUiInterface);
        usersLocalDataSource = new UserLocalDataSource(new AppExecutors(), database.localUserDao());
        usersRemoteDataSource = new UserRemoteDataSource(mUiInterface);
    }

    @Override
    public void getPhoneLogin(String telephone, String password,String longitude,String latitude,String JpushId, boolean cached) {
//        if (cached) {
//            LocalUserInfo localUserInfo = getLocalUser(telephone);
//            if (localUserInfo != null) {
//                KyeLogUtils.i("show","cached memory");
//                getUiInterface().loadDataSucess(localUserInfo);
//                return;
//            }
//        }

        usersLocalDataSource.getUsers(telephone,password,longitude,latitude,JpushId, new UsersDataSource.GetUsersCallBack() {
            @Override
            public void onUserLocalLoad(LocalUserInfo userInfos) {
                if(cached){
                    if(mCachedTasks==null){
                        mCachedTasks = new LinkedHashMap<>();
                    }
                    mCachedTasks.put(telephone,userInfos);
                }
                KyeLogUtils.i("show","get  database");
                //切换显示进程
                Flowable.just(userInfos).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LocalUserInfo>() {
                    @Override
                    public void accept(LocalUserInfo localUserInfo) throws Exception {
                        getUiInterface().loadDataSucess(localUserInfo);
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                KyeLogUtils.i("show","database not data ");
                usersRemoteDataSource.getUsers(telephone,password,longitude,latitude,JpushId, new UsersDataSource.GetUsersCallBack() {
                    @Override
                    public void onUserLocalLoad(LocalUserInfo userInfos) {
                        if(cached){
                            if(mCachedTasks==null){
                                mCachedTasks = new LinkedHashMap<>();
                            }
                            mCachedTasks.put(telephone,userInfos);
                            KyeLogUtils.i("show","save memory");
                            usersLocalDataSource.save(userInfos);
                        }
                        getUiInterface().loadDataSucess(userInfos);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        });
    }

    public LocalUserInfo getLocalUser(String id) {
        checkNotNull(id);
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(id);
        }
    }
}
