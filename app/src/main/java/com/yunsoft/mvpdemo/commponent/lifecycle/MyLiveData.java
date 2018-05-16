package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-15 18:24
 * Description:this is MyLiveData
 * 继承LiveData
 */

public class MyLiveData extends LiveData<String> {

    private SharedPreferences sharedPreferences;

    private static MyLiveData mInstance;

    public static MyLiveData getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new MyLiveData(application);
        }
        return mInstance;
    }

    public MyLiveData(Application application) {
        if (sharedPreferences == null) {
            sharedPreferences = application.getSharedPreferences("share", Context.MODE_PRIVATE);
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.e("show","onActive");
        setValue(sharedPreferences.getString("user",""));
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.e("show","onInactive");
    }

    public void setLocalUser(String user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user",user);
        Log.e("show","setLocalUser");
        editor.commit();
    }
}
