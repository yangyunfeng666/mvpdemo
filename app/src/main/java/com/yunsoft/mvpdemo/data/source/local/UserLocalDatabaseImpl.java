package com.yunsoft.mvpdemo.data.source.local;

import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-23 16:16
 * Description:this is UserLocalDatabaseImpl
 * 定义本地存储额外的存储接口
 */


public interface UserLocalDatabaseImpl {
    //保存数据
    void  save(LocalUserInfo localUserInfo);
}
