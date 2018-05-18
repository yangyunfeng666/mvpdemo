package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.yunsoft.mvpdemo.data.source.UsersRespository;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-17 16:47
 * Description:this is UserProfileViewModel
 */

public class UserProfileViewModel extends ViewModel {
    private UsersRespository respository;

    public void setRespository(UsersRespository respository) {
        this.respository = respository;
    }
    private LiveData<LocalUser> userLiveData;

    private MutableLiveData<String> userId =new MutableLiveData<>();

    public void init(String s) {
        userId.setValue(s);
        if(userLiveData!=null){
            //
        }
//        userLiveData=  Transformations.switchMap(userId,userid->respository.getUsers(userid));
//        userLiveData = respository.getUsers(userId);
    }


    public LiveData<LocalUser> getUserLiveData() {
        return userLiveData;
    }
}
