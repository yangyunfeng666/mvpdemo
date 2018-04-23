package com.yunsoft.mvpdemo.persistence.perf;

import android.content.Context;

import com.kye.basemodule.shareperfence.Preference;

/**
 * Created by yyf on 2018-04-13 17:43.
 */

public class SharePreHelper {
    /**
     * 定义存储的名称
     */
    private static final String PREFERENCE_FILE_NAME = "share";

    public static void init(Context context){
        Preference.init(context,PREFERENCE_FILE_NAME);
    }
    /**
     * 比如存储一个字符串 例子
     */
    public static void saveMyString(){
        Preference.set("mystring","ddddd");
    }

}
