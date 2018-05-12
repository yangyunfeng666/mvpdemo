package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.storage.StorageManager;

import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.math.BigDecimal;

import io.reactivex.internal.fuseable.SimplePlainQueue;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 16:14
 * Description:this is MyData
 */


public class NameViewModel extends ViewModel {

    private MutableLiveData<String> mNameLiveData;

    public MutableLiveData<String> getmNameLiveData(){
        if(mNameLiveData==null){
              mNameLiveData = new MutableLiveData<String>();
        }
        return mNameLiveData;
    }

}
