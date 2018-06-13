package com.yunsoft.mvpdemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.pojo.UpdateModel;
import com.yunsoft.reactnativeupdate.DrawableModel;
import com.yunsoft.reactnativeupdate.FileConstant;
import com.yunsoft.reactnativeupdate.MutilDownHelper;
import com.yunsoft.reactnativeupdate.ReactNativePreLoader;
import com.yunsoft.reactnativeupdate.UpdateUtil;
import com.yunsoft.reactnativeupdate.hotupdate.HotUpdate;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.List;


/**
 * Author: yangyunfeng
 * Date: 公元2018-5-30 10:55
 * Description:this is ReactiveActivity
 */

public class ReactiveActivity extends AppCompatActivity {

    private Button refresh_btn;
    private Button oldVersionUpdate_btn;
    private Button allUpdate_btn;
    private Button back_btn;
    private Button into_btn;
    public static final int DOWNLOAD_FINISH = 10202;
    private UpdateModel updateModel;//模拟网络更新得到的model对象

    //全量更新
    private String downUrl = "https://raw.githubusercontent.com/yangyunfeng666/image/master/bundle.zip";
    //增量更新
    private String addUpdate = "https://raw.githubusercontent.com/yangyunfeng666/image/master/new/bundle.zip";
    //增量更新1.0.3在1.0.1上的增量更新版本下载地址
    private String newUpdateUrl = "https://raw.githubusercontent.com/yangyunfeng666/image/master/add/bundle.zip";

    //更新通知hander
    private class MyHander extends Handler {
        SoftReference<ReactiveActivity> mActivity;

        public MyHander(ReactiveActivity reactiveActivity) {
            mActivity = new SoftReference<>(reactiveActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() != null && !mActivity.get().isFinishing()) {
                switch (msg.what) {
                    case HotUpdate.UNZIP_SUCCESS://解压合并完成 注册组件和本地保存版本
                        Toast.makeText(mActivity.get(), "更新完成", Toast.LENGTH_SHORT).show();
                        String version = (String) msg.obj;
                        //手动注册组件
                        ((MyApplication) mActivity.get().getApplication()).setVersion(version); //设置版本
                        ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
                        SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
                        updateShare.edit().putString("reactive_version", version).apply();
                        break;
                    case DOWNLOAD_FINISH://解压处理合并处理
                        Log.e("show", "version:" + "解压");
                        HotUpdate.handleZIP(getApplicationContext(), mActivity.get().updateModel.getNow_version(), mActivity.get().updateModel.getOld_version(), mActivity.get().updateModel.isAllUpdate(), mActivity.get().handler);
                        break;
                }
            }
        }
    }

    private MyHander handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);
        refresh_btn = findViewById(R.id.refresh_btn);
        oldVersionUpdate_btn = findViewById(R.id.oldVersionUpdate_btn);
        allUpdate_btn = findViewById(R.id.allUpdate_btn);
        back_btn = findViewById(R.id.back_btn);
        into_btn = findViewById(R.id.into_btn);
        handler = new MyHander(this);

        allUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全量更新
                updateModel = new UpdateModel("1.0.2", "", true, false,downUrl);
                update(updateModel);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回退
                updateModel = new UpdateModel("1.0.1", "", false, true,"");
                update(updateModel);
            }
        });

        oldVersionUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增量更新 以旧版本更新
                updateModel = new UpdateModel("1.0.3", "1.0.1", false, false,addUpdate);
                update(updateModel);
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增量更新
                updateModel = new UpdateModel("1.0.1", "1.0.0", false, false,newUpdateUrl);
                update(updateModel);
            }
        });

        //查看版本更新结果
        into_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReactiveActivity.this, ReactNativeActivity.class);
                startActivity(intent);
            }
        });

        //设置调试模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1002);
            }
        }

        //写文件权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        }
        //注册react 入口组件
        registLocalVersion();

    }

    /**
     * 注册本地版本
     */
    public void registLocalVersion() {
        SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
        String version = updateShare.getString("reactive_version", "");
        Toast.makeText(this, "init version" + version, Toast.LENGTH_SHORT).show();
        ((MyApplication) getApplication().getApplicationContext()).setVersion(version); //设置版本
        ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
    }

    public void update(UpdateModel updateModel) {
        if (updateModel.isBackToOld()) {
            SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
            updateShare.edit().putString("reactive_version", updateModel.getNow_version()).apply();
            ((MyApplication) getApplication().getApplicationContext()).setVersion(updateModel.getNow_version()); //设置版本
            ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
        } else {
            Toast.makeText(this, "真正更新，请稍后...", Toast.LENGTH_SHORT).show();
            requstPermission(updateModel.getDownurl(), updateModel.getNow_version()); //下载更新好了，更新版本
        }
    }


    public void requstPermission(String RemoteUrl, String version) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        } else {
            downLoadBundle(RemoteUrl, version);
        }
    }

    private void downLoadBundle(final String RemoteUrl, final String now_version) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MutilDownHelper downHelper = new MutilDownHelper();
                int result = downHelper.load(RemoteUrl, FileConstant.getInstance().JS_PATCH_LOCAL_PATH, Runtime.getRuntime().availableProcessors() + 1, now_version);
                if (result == 1) {
                    handler.sendEmptyMessage(DOWNLOAD_FINISH);
                } else {
                    Log.e("show", "下载失败");
                }
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //第一次就移动assets开头的图片到sdcard里面
                    SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
                    String update = updateShare.getString("firstUpdate", "0");
                    if (update.equals("0")) {
                        //assets 是根据你本地文件在到包成react-native
                        final List<DrawableModel> models = UpdateUtil.getResourceByReflect(R.drawable.class,"assets");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (DrawableModel model : models) {
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), model.getId());
                                    if (bitmap != null) {
                                        UpdateUtil.saveBitMapToSdcard(bitmap, FileConstant.getInstance().DRAWABLE_PATH, FileConstant.getInstance().DRAWABLE_PATH + File.separator + model.getName() + ".png", Bitmap.CompressFormat.PNG);
                                    }
                                }
                            }
                        }).start();
                        updateShare.edit().putString("firstUpdate", "1").apply();
                    }
                }
                break;
            case 1002:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(this)) {
                        // SYSTEM_ALERT_WINDOW permission not granted...
                        Toast.makeText(this, "需要授权", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
