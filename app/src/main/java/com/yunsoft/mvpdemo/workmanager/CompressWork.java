package com.yunsoft.mvpdemo.workmanager;


import android.support.annotation.NonNull;

import androidx.work.Worker;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-20 14:48
 * Description:this is CompressWork
 */

public class CompressWork extends Worker {

    @NonNull
    @Override
    public Result doWork() {
        Comparess();
        return Result.SUCCESS;
    }
    //压缩文件
    private void Comparess() {

    }
}
