package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 11:49
 * Description:this is Book
 */
//这里使用了外键 entity是子
@Entity(tableName = "book",foreignKeys = @ForeignKey(entity = LocalUser.class,parentColumns ="id",childColumns = "userid" ,onDelete = ForeignKey.CASCADE)
,indices = {@Index("userid")}
)
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userid;
    private String bookName;
    private float price;
    private Date datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
