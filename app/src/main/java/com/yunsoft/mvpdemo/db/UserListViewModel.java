package com.yunsoft.mvpdemo.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.yunsoft.mvpdemo.MyApplication;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 18:05
 * Description:this is UserListViewModel
 */

public class UserListViewModel extends AndroidViewModel {

    //监听数据的数据变化
    private MediatorLiveData<List<LocalUser>> mediatorLiveData;


   public UserListViewModel(Application application){
       super(application);
       LiveData<List<LocalUser>> mListLocalUser = ((MyApplication)application).getRepository().getLocalUserData();
        mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.setValue(null);
        mediatorLiveData.addSource(mListLocalUser,mediatorLiveData::setValue);
    }

    public MediatorLiveData<List<LocalUser>> getUserListData(){
       return mediatorLiveData;
    }


}
