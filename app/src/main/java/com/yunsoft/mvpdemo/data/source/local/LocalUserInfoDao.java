package com.yunsoft.mvpdemo.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yunsoft.mvpdemo.data.LocalUserInfo;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 19:26
 * Description:this is LocalUserDao
 * room 数据库存储的dao类
 */


@Dao
public interface LocalUserInfoDao {
    /**
     * 查看所有的用户
     *
     * @return
     */
    @Query("SELECT * FROM LocalUserInfo")
    List<LocalUserInfo> getUsers();
    /**
     * 根据id 读取用户
     *
     * @param telephone
     * @return
     */
    @Query("select * from LocalUserInfo where telephone =:telephone")
    LiveData<LocalUserInfo> getLiveUserById(String telephone);

    /**
     * 根据id 读取用户
     *
     * @param telephone
     * @return
     */
    @Query("select * from LocalUserInfo where telephone =:telephone")
    LocalUserInfo getUserById(String telephone);

    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(LocalUserInfo task);
    //更新数据
    @Update
    void updateUsers(LocalUserInfo userInfo);


}
