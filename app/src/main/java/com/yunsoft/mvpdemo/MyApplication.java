package com.yunsoft.mvpdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.kye.basemodule.log.KyeLogUtils;
import com.tencent.bugly.beta.Beta;
import com.yunsoft.mvpdemo.dagger.ActivityComponent;
import com.yunsoft.mvpdemo.dagger.AppComponent;
import com.yunsoft.mvpdemo.dagger.AppModule;
import com.yunsoft.mvpdemo.dagger.DaggerActivityComponent;
import com.yunsoft.mvpdemo.dagger.DaggerAppComponent;
import com.yunsoft.mvpdemo.dagger.DaggerMyAppCommponent;
import com.yunsoft.mvpdemo.data.AppExecutors;
import com.yunsoft.mvpdemo.db.AppDatabase;
import com.yunsoft.mvpdemo.db.DaoMaster;
import com.yunsoft.mvpdemo.db.DaoSession;
import com.yunsoft.mvpdemo.db.DataRepository;
import com.yunsoft.mvpdemo.persistence.perf.SharePreHelper;
import com.yunsoft.mvpdemo.persistence.sqlite.UpdateOpenHelper;
import com.yunsoft.reactnativeupdate.FileConstant;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by yyf on 2018-04-11 15:37.
 */

public class MyApplication extends Application implements HasActivityInjector ,ReactApplication {
    private static MyApplication mInstance;

    private DaoSession daoSession;

    private AppComponent mAppComponent;
    private ActivityComponent mActivityComponent;
    private AppExecutors mAppExecutors;


    //注入 DispatchingAndroidInjector 在 DaggerMyAppCommponent什么时候填入
    //管理XXXXActivityProvider
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    //新版本的版本号
    private String version = "";


    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SoLoader.init(this,  false);
        KyeLogUtils.init(this, BuildConfig.BUGLY_ID, BuildConfig.LOG_TAG);
        SharePreHelper.init(this);//shareperfence初始化
        setDataBase();
        //component实例
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mActivityComponent = DaggerActivityComponent.builder().appModule(new AppModule(this)).build();
        DaggerMyAppCommponent.builder().build().inject(this);
        mAppExecutors = new AppExecutors();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        //安装tinker
        Beta.installTinker();
    }

    private void setDataBase() {
        //这里使用了加密的数据库，加密的数据库密码是 dddd
        //这个不更新数据使用
//       DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db");
        //这个需要更新数据库使用
        UpdateOpenHelper devOpenHelper = new UpdateOpenHelper(this, "notes-db");
        Database database = devOpenHelper.getEncryptedWritableDb("ddd");
        daoSession = new DaoMaster(database).newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    public AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    public AppDatabase getDataBase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDataBase());
    }
    /**
     *包名
     */
    public String getAppPackageName() {
        return this.getPackageName();
    }

//    private ReactNativeHost ReactNativeHost =

    @Override
    public ReactNativeHost getReactNativeHost() {
        return  new ReactNativeHost(this) {
            @Override
            public boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }

            @Override
            protected List<ReactPackage> getPackages() {
                return Arrays.asList(new MainReactPackage());
            }

            @Nullable
            @Override
            protected String getJSBundleFile() {
                if("".equals(version)) return super.getJSBundleFile();
                //判断新版本的bundle文件时候存在
                File file = new File (FileConstant.getInstance().JS_BUNDLE_LOCAL_PATH+version+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
                if(file != null && file.exists()) {
                    return FileConstant.getInstance().JS_BUNDLE_LOCAL_PATH+version+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE;
                } else {
                    return super.getJSBundleFile();
                }
            }
        };
    }
}
