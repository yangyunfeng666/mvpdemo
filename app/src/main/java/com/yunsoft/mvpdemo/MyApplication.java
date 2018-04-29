package com.yunsoft.mvpdemo;

import android.app.Activity;
import android.app.Application;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.dagger.ActivityComponent;
import com.yunsoft.mvpdemo.dagger.DaggerActivityComponent;
import com.yunsoft.mvpdemo.dagger.DaggerAppComponent;
import com.yunsoft.mvpdemo.dagger.DaggerMyAppCommponent;
import com.yunsoft.mvpdemo.db.DaoMaster;
import com.yunsoft.mvpdemo.db.DaoSession;
import com.yunsoft.mvpdemo.dagger.AppComponent;
import com.yunsoft.mvpdemo.dagger.AppModule;
import com.yunsoft.mvpdemo.persistence.perf.SharePreHelper;
import com.yunsoft.mvpdemo.persistence.sqlite.UpdateOpenHelper;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by yyf on 2018-04-11 15:37.
 */

public class MyApplication extends Application implements HasActivityInjector {

    private static MyApplication mInstance;

    private DaoSession daoSession;

    private AppComponent mAppComponent;
    private ActivityComponent mActivityComponent;

    //注入 DispatchingAndroidInjector 在 DaggerMyAppCommponent什么时候填入
    //管理XXXXActivityProvider
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        KyeLogUtils.init(this,BuildConfig.BUGLY_ID,BuildConfig.LOG_TAG);
        SharePreHelper.init(this);//shareperfence初始化
        setDataBase();
        //component实例
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mActivityComponent =DaggerActivityComponent.builder().appModule(new AppModule(this)).build();
        DaggerMyAppCommponent.builder().build().inject(this);
    }


    private void setDataBase(){
        //这里使用了加密的数据库，加密的数据库密码是 dddd
        //这个不更新数据使用
//       DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db");
        //这个需要更新数据库使用
        UpdateOpenHelper devOpenHelper = new UpdateOpenHelper(this,"notes-db");
        Database database = devOpenHelper.getEncryptedWritableDb("ddd");
        daoSession = new DaoMaster(database).newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    public AppComponent getAppComponent(){
        return  mAppComponent;
    }

    public ActivityComponent getActivityComponent(){
        return  mActivityComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
