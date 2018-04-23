package com.yunsoft.mvpdemo.persistence.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.db.DaoMaster;
import com.yunsoft.mvpdemo.db.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by yyf on 2018-04-13 14:27.
 * 在升级的时候使用的OpenHelper
 */

public class UpdateOpenHelper extends DaoMaster.OpenHelper {

    public UpdateOpenHelper(Context context, String name) {
        super(context, name);
    }

    public UpdateOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//        if(oldVersion<newVersion) {
            //操作数据库的更新 有几个表升级都可以传入到下面，需要更新的dao
            MigrationHelper.getInstance().migrate(db,
                    UserDao.class);
//        }
    }

}
