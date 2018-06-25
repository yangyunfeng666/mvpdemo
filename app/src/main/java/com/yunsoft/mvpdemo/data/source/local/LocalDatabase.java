package com.yunsoft.mvpdemo.data.source.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
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
                        LocalDatabase.class,"test.db")
                        .allowMainThreadQueries()//允许在主线程查询数据库
                        .addMigrations()
//                        .addMigrations(MIGRATION_1_2) 版本升级使用
                        .fallbackToDestructiveMigration()//迁移数据库失败时候会重新创建数据库
                        .build();
            }
        }
        return  INSTANCE;
    }

    //升级使用 从哪个版本升级到哪个版本 然后是修改的sql 修改的部分
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };

    @Override
    public void clearAllTables() {

    }
}
