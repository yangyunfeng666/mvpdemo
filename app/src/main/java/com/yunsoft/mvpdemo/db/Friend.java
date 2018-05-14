package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 15:37
 * Description:this is Friend
 */
@Entity
public class Friend {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String friendNickName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }
}
