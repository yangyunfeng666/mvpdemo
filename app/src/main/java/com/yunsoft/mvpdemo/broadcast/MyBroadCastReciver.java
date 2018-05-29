package com.yunsoft.mvpdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-27 15:25
 * Description:this is MyBroadCastReciver
 */

public class MyBroadCastReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        abortBroadcast();
    }
}
