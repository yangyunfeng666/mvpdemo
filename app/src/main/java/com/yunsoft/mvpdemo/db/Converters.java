package com.yunsoft.mvpdemo.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-14 12:19
 * Description:this is Converters
 */

public class Converters {
    @TypeConverter
    public static Date fromTimeStamp(Long timestap){
       return timestap==null?null:  new Date(timestap);
    }
    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date==null?null:  date.getTime();
    }
}
