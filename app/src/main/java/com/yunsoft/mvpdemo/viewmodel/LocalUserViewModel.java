package com.yunsoft.mvpdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.db.LocalUserDao;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-15 16:33
 * Description:this is LocalUserViewModel
 */

public class LocalUserViewModel extends ViewModel {
    //LiveData 包含的有分页数据的
    public LiveData<PagedList<LocalUser>> pagedListLiveData;

    public LocalUserViewModel(LocalUserDao dao){
        //从dao中得到20个数据
        pagedListLiveData = new LivePagedListBuilder<>(dao.usersByLastName(),20).build();
    }
}
