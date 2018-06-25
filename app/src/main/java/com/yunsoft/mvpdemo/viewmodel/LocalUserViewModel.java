package com.yunsoft.mvpdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.db.LocalUserDao;

import java.util.concurrent.Executor;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-15 16:33
 * Description:this is LocalUserViewModel
 */

public class LocalUserViewModel extends ViewModel {
    //LiveData 包含的有分页数据的
    public LiveData<PagedList<LocalUser>> pagedListLiveData;

    public LocalUserViewModel(LocalUserDao dao, Executor executor){
        //从dao中得到20个数据
        PagedList.Config.Builder builder = new  PagedList.Config.Builder();
        builder.setPageSize(20).setPrefetchDistance(100).setEnablePlaceholders(false);
        pagedListLiveData = new LivePagedListBuilder<>(dao.usersByLastName(),builder.build()).setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<LocalUser>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
