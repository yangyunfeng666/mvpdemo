package com.yunsoft.mvpdemo.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.react.JSCConfig;
import com.facebook.react.ReactActivity;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.reactnative.FileConstant;
import com.yunsoft.mvpdemo.reactnative.hotupdate.HotUpdate;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-30 10:55
 * Description:this is ReactiveActivity
 */

public class ReactiveActivity extends AppCompatActivity {
    private static final String TAG = "ReactiveActivity";
    private Button update_btn;
    private Button into_btn;
    private File zipfile;
    private long mDownLoadId;
    private CompleteReceiver localReceiver;
    private String downUrl = "https://raw.githubusercontent.com/yangyunfeng666/image/master/bundle.zip";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);
        update_btn = findViewById(R.id.update_btn);
        into_btn = findViewById(R.id.into_btn);
        //注册广播
        registeReceiver();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requstPermission(downUrl);
            }
        });

        into_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ReactiveActivity.this,ReactNativeActivity.class);
                Intent intent = new Intent(ReactiveActivity.this,ReactNativeActivityBak.class);
                startActivity(intent);
            }
        });
    }

    public void requstPermission(String RemoteUrl){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1001);
        }else{
            downLoadBundle( RemoteUrl);
        }
    }

    private void downLoadBundle(String RemoteUrl) {
        // 1.下载前检查SD卡是否存在更新包文件夹
        HotUpdate.checkPackage(getApplicationContext(),FileConstant.JS_BUNDLE_LOCAL_PATH);
        // 2.下载
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager
                .Request(Uri.parse(RemoteUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://"+ FileConstant.JS_PATCH_LOCAL_PATH));
        mDownLoadId = downloadManager.enqueue(request);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1001:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    downLoadBundle( downUrl);;
                }
                break;
        }
    }

    /**
     * 注册广播
     */
    private void registeReceiver() {
        localReceiver = new CompleteReceiver();
        registerReceiver(localReceiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public class CompleteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            if(completeId == mDownLoadId) {
                HotUpdate.handleZIP(getApplicationContext());
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(localReceiver);
    }
}
