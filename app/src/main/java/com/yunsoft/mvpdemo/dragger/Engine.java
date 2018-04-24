package com.yunsoft.mvpdemo.dragger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 14:13
 * Description:this is Engine
 */


public class Engine {


    private String s;
    Engine(String s){
        this.s = s;
    }

    public String run(){
        Log.e("Engine","run"+s);
        return s;
//
    }
}
