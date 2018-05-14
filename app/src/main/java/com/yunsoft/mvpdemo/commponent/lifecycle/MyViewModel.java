package com.yunsoft.mvpdemo.commponent.lifecycle;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.yunsoft.mvpdemo.db.AppDatabase;
import com.yunsoft.mvpdemo.persistence.sqlite.dao.User;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-12 17:12
 * Description:this is MyViewModel
 * 继承于ViewModel
 */

public class MyViewModel extends ViewModel {

    //实例一个LiveData 对象
    private MutableLiveData<List<User>> list;

   public MutableLiveData<List<User>> getList(){
        if(list==null){
            list =  new MutableLiveData<>();
        }
        loadData();
        //加载逻辑
        return  list;
    }
    //加载逻辑
    private void loadData() {

    }


}
