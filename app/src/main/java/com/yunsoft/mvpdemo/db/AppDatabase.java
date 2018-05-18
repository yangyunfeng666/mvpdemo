package com.yunsoft.mvpdemo.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.print.PrintAttributes;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.data.source.local.LocalUserInfoDao;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 16:56
 * Description:this is AppDatabase
 */
//有几张表就在 entities里面添加，而且要定义对表操作的抽象方法，返回值就是抽象类 version是数据库版本号 是升级的触发条件
@Database(entities = {LocalUser.class,Book.class,Friend.class, LocalUserInfo.class},version = 3,exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract LocalUserDao LocalUserDao();
    public abstract LocalUserInfoDao LocalUserInfoDao();
    public abstract BookDao BookDao();
    private static String Db_name = "Mydb.db";//定义数据的名称
    private  static volatile AppDatabase mInstance;//单例定义代码
    /**
     * 返回单例对象
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context, AppExecutors appExecutors){
        if(mInstance==null){
            synchronized (AppDatabase.class){
                if(mInstance ==null){
                    mInstance = Room.databaseBuilder(context,AppDatabase.class,Db_name)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    //做些操作
                                    appExecutors.diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    });
                                }
                            })
                            .allowMainThreadQueries()//允许在主线程操作 但是最好不要这样做，因为会锁着主线程，
                            // 所以使用liveData 或者Rxjava类返回结果，异步更好
//                            .addMigrations(migration)//版本升级使用 可以保存以前数据 如果数据库变化，但是不提供migration，那么以前的数据库的数据会丢失。
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mInstance;
    }

  public static Migration migration = new Migration(2,3) {
       @Override
       public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("create table `Frend`(`id` INTEGER,`name` TEXT,PRIMARY KEY(`id`))");
//            database.
//            database.execSQL("ALTER TABLE localuser ADD COLUMN publics TEXT  ");
//            database.execSQL("crate TABLE localuser ADD COLUMN publics TEXT  ");
       }
   };



    public void init(){
        LocalUser localUser = new LocalUser();
        localUser.setAge(1);
        localUser.setSex("男");
        localUser.setUsername("阳离子");
        LocalUserDao dao = LocalUserDao();
        dao.insert(localUser);

        Log.d("database","数据库：Id"+localUser.getId()+"");
    }

}
