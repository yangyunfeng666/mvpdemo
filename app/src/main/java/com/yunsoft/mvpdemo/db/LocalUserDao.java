package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 16:49
 * Description:this is LocalUserDao
 */

@Dao
public interface LocalUserDao {
    @Query("select * from localuser")
    Maybe<List<LocalUser>> quertAll();//使用rxjava maybe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert(LocalUser localUser);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void update(LocalUser localUser);

    @Delete
    void  delete(LocalUser localUser);
    //这里是ages在那些阶段的
    @Query("select username,age from localuser where age in(:ages) ")
    List<showData> quert(List<Integer> ages );

    public class showData{
        //这里必须要get set属性
        @ColumnInfo(name = "username")
        private String name;
        @ColumnInfo(name = "age")
        private int age;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
