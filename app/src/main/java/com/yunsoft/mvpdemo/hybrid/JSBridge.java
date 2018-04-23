package com.yunsoft.mvpdemo.hybrid;

import android.text.TextUtils;
import android.util.ArrayMap;

/**
 * Created by yyf on 2018-04-17 16:38.
 * 因为类就是scheme
 */

public class JSBridge {
    //url 的host对象
    public static final String BRIDGE_NAME = "JSBridge";

    private static JSBridge INSTANCE = new JSBridge();

    private boolean isEnable=true;

    private ArrayMap<String, Class<? extends IInject>> mClassMap = new
            ArrayMap<>();

    private JSBridge() {
        //注册一个业务类在里面
        mClassMap.put(BRIDGE_NAME, JSLogical.class);
    }

    public static JSBridge getInstance() {
        return INSTANCE;
    }

    public boolean addInjectPair(String name, Class<? extends IInject> clazz) {
        if (!mClassMap.containsKey(name)) {
            mClassMap.put(name, clazz);
            return true;
        }
        return false;
    }

    public boolean removeInjectPair(String name,Class<? extends IInject> clazz) {
        if (TextUtils.equals(name,BRIDGE_NAME)) {
            return false;
        }
        Class clazzValue=mClassMap.get(name);
        if(null!=clazzValue && (clazzValue == clazz)){
            mClassMap.remove(name);
            return true;
        }
        return false;

    }


    public ArrayMap<String, Class<? extends IInject>> getInjectPair() {
        return mClassMap;
    }
}
