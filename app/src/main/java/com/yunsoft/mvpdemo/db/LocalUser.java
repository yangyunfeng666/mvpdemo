package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
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
    private String publics;

    public LocalUser() {
    }

    private LocalUser(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setSex(builder.sex);
        setAge(builder.age);
        setPublics(builder.publics);
    }

    public String getPublics() {
        return publics;
    }

    public void setPublics(String publics) {
        this.publics = publics;
    }

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

    @Override
    public String toString() {
        return "LocalUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", publics='" + publics + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static final class Builder {
        private int id;
        private String username;
        private String sex;
        private int age;
        private String publics;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder sex(String val) {
            sex = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder publics(String val) {
            publics = val;
            return this;
        }

        public LocalUser build() {
            return new LocalUser(this);
        }
    }
}
