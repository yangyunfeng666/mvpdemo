package com.yunsoft.mvpdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 10:31
 * Description:this is UserProfileViewModel
 */


public class UserProfileViewModel extends ViewModel {
    private String userid;
    private LiveData<User> user;

    public void init(String uid){
        this.userid = uid;
    }

    private LiveData<User> getUser(){
        return  user;
    }

}
