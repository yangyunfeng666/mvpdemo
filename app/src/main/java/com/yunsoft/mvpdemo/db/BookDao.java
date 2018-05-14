package com.yunsoft.mvpdemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.yunsoft.mvpdemo.activity.LiveDataActivity;

import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 11:54
 * Description:this is BookDao
 */
@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insert(Book book);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    public void update(Book book);

    @Delete
    public void delete(Book book);

    @Query("select * from book where id =:id")
    public List<Book> query(int id);

    @Query("select * from book where id =:id")
    public LiveData<List<Book>> querySync(int id);//查询数据结果自动更新
}
