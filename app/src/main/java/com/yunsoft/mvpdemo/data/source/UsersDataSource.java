package com.yunsoft.mvpdemo.data.source;

import android.support.annotation.NonNull;

import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:42
 * Description:this is UsersDataSource
 * 定义返回数据的接口回调和请求数据的接口
 */


public interface UsersDataSource {

    //写网络读取端口
    interface GetUsersCallBack{//网络读取接口回调
        void onUserLocalLoad(LocalUserInfo userInfos);
        void onDataNotAvailable();
    }

    //网络读取
    void getUsers(String telephone, String password,String longitude,String latitude,String JpushId,GetUsersCallBack callBack);

}
