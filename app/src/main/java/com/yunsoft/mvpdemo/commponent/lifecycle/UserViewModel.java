package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;

import com.yunsoft.mvpdemo.db.LocalUser;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-15 10:52
 * Description:this is UserViewModel
 */

public class UserViewModel extends ViewModel{

    private LiveData<String> mutableLiveData;

    public LiveData<String> getLocalData(){
        if(mutableLiveData==null) {
            mutableLiveData = new LiveData<String>(){
                @Override
                protected void onActive() {
                    super.onActive();
                }
            };
        }
        return mutableLiveData;
    }


}
