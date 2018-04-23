package com.yunsoft.mvpdemo.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-23 14:52
 * Description:this is LocalDatabase
 * room Database 处理
 */

@Database(entities = {LocalUserInfo.class},version = 1,exportSchema = false)// 声明要有哪些类 数据版本
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;
    public abstract LocalUserInfoDao localUserDao();
    private static final Object sLock = new Object();
    public static LocalDatabase getInstance(Context context){
        synchronized (sLock){
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LocalDatabase.class,"test.db").build();
            }
        }
        return  INSTANCE;
    }

}
