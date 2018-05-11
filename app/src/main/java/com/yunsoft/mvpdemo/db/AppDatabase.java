package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 16:56
 * Description:this is AppDatabase
 */

@Database(entities = {LocalUser.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LocalUserDao LocalUserDao();
    private static String Db_name = "Mydb.db";//定义数据的名称
    private  static volatile AppDatabase mInstance;

    /**
     * 返回单例对象
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context){
        if(mInstance==null){
            synchronized (AppDatabase.class){
                if(mInstance ==null){
                    mInstance = Room.databaseBuilder(context,AppDatabase.class,Db_name)
                            .allowMainThreadQueries()//允许在主线程操作
                            .build();
                }
            }
        }
        return mInstance;
    }


}
