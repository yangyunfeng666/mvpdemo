package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-10 16:45
 * Description:this is LocalUser
 */

@Entity(tableName = "localuser")
public class LocalUser {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    @ColumnInfo(name = "sex")
    private String sex;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
