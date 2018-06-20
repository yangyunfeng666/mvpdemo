package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.Observer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.workmanager.CompressWork;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-20 14:46
 * Description:this is WorkManagerActivity
 */

public class WorkManagerActivity extends AppCompatActivity {
private Button work_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanager);
        work_btn = findViewById(R.id.work_btn);
        //声明什么情况下运行 比如在网络连接，存储高，充电，空闲时候进行任务
        Constraints.Builder constraintsbuilder = new  Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresStorageNotLow(true).setRequiresCharging(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraintsbuilder.setRequiresDeviceIdle(true);
        }
        //声明任务
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(CompressWork.class).setConstraints(constraintsbuilder.build()).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
        WorkManager.getInstance().getStatusById(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus workStatus) {
                //当work结束时候的判断
                if(workStatus!=null&&workStatus.getState().isFinished()){

                }
            }
        });
        work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().cancelWorkById(oneTimeWorkRequest.getId());
            }
        });

        PeriodicWorkRequest.Builder requestbuild =new  PeriodicWorkRequest.Builder(CompressWork.class,12, TimeUnit.HOURS).setConstraints(constraintsbuilder.build());
       PeriodicWorkRequest request = requestbuild.build();
        WorkManager.getInstance().enqueue(request);

//        WorkManager.getInstance().beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest).enqueue();
        }
}
