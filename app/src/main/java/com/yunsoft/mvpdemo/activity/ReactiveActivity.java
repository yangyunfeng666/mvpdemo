package com.yunsoft.mvpdemo.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.reactnative.DrawableModel;
import com.yunsoft.mvpdemo.reactnative.FileConstant;
import com.yunsoft.mvpdemo.reactnative.ReactNativePreLoader;
import com.yunsoft.mvpdemo.reactnative.UpdateModel;
import com.yunsoft.mvpdemo.reactnative.hotupdate.HotUpdate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-30 10:55
 * Description:this is ReactiveActivity
 */

public class ReactiveActivity extends AppCompatActivity {
    private static final String TAG = "ReactiveActivity";
    private Button update_btn;
    private Button into_btn;
    private Button refresh_btn;
    private Button back_btn;
    private Button old_version_update_btn;
    private long mDownLoadId;
    private CompleteReceiver localReceiver;
    //全量更新
    private String downUrl = "https://raw.githubusercontent.com/yangyunfeng666/image/master/bundle.zip";
    //增量更新
    private String addUpdate = "https://raw.githubusercontent.com/yangyunfeng666/image/master/new/bundle.zip";

    //增量更新1.0.3在1.0.1上的增量更新版本下载地址
    private String newUpdateUrl = "https://raw.githubusercontent.com/yangyunfeng666/image/master/add/bundle.zip";

    //是否是全量更新 就是因为如果每次都是差量更新，是2个版本的更新，如果是全量更新就是删除以前的所以文件，然后以当前的文件的jsbundly为主
    private String now_version = "";//新的版本号jsbunder
    private String old_version = "";//旧的版本号jsbunder
    private boolean AllUpdate = false;//是否全量更新
    private boolean backToOld = false;//版本回退到以前版本

    private UpdateModel updateModel;//模拟网络读取数据
    private static final int UPDATE = 1001;

    private class MyHander extends Handler {
        SoftReference<ReactiveActivity> mActivity;

        public MyHander(com.yunsoft.mvpdemo.activity.ReactiveActivity reactiveActivity) {
            mActivity = new SoftReference<>(reactiveActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() != null && !mActivity.get().isFinishing()) {
                switch (msg.what = UPDATE) {
                    case 1001:
                        Toast.makeText(mActivity.get(),"更新完成",Toast.LENGTH_SHORT).show();
                        String version = (String) msg.obj;
                        Log.e("show", "version:" + version);
                        ((MyApplication) mActivity.get().getApplication()).setVersion(version); //设置版本
                        ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
                        SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
                        updateShare.edit().putString("reactive_version", version).apply();
                }
            }
        }
    }

    private MyHander handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);
        update_btn = findViewById(R.id.update_btn);
        into_btn = findViewById(R.id.into_btn);
        refresh_btn = findViewById(R.id.refresh_btn);
        back_btn = findViewById(R.id.back_btn);
        old_version_update_btn = findViewById(R.id.old_version_update_btn);
        //注册广播
//        registeReceiver();
        handler = new MyHander(this);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全量更新
                updateModel = new UpdateModel("1.0.2", "", true, false);
                update(updateModel,downUrl);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回退
                updateModel = new UpdateModel("1.0.1", "", false, true);
                update(updateModel,"");
            }
        });
        old_version_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增量更新 以旧版本更新
                updateModel = new UpdateModel("1.0.3", "1.0.1", false, false);
                update(updateModel,newUpdateUrl);
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增量更新
                updateModel = new UpdateModel("1.0.1", "1.0.0", false, false);
                update(updateModel,addUpdate);
            }
        });

        into_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReactiveActivity.this, ReactNativeActivity.class);
                startActivity(intent);
            }
        });

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
        noticeLocalVersion();
    }

    public void noticeLocalVersion() {
        SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
        String version = updateShare.getString("reactive_version", "");
        Toast.makeText(this,"init version"+version,Toast.LENGTH_SHORT).show();
        ((MyApplication) getApplication().getApplicationContext()).setVersion(version); //设置版本
        ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
    }

    public void update(UpdateModel updateModel,String downUrl) {

        now_version = updateModel.getNow_version();
        old_version = updateModel.getOld_version();
        backToOld = updateModel.isBackToOld();
        AllUpdate = updateModel.isAllUpdate();
        if (backToOld) {
            SharedPreferences updateShare = getSharedPreferences("update", Context.MODE_PRIVATE);
            updateShare.edit().putString("reactive_version", now_version).apply();
            ((MyApplication) getApplication().getApplicationContext()).setVersion(now_version); //设置版本
            ReactNativePreLoader.preLoad(ReactiveActivity.this, "test");//重新加载数据
        } else {
            Toast.makeText(this,"真正更新，请稍后...",Toast.LENGTH_SHORT).show();
            requstPermission(downUrl); //下载更新好了，更新版本
        }
    }

    public void saveBitMapToSdcard(Bitmap bitmap, String dir, String path, Bitmap.CompressFormat compressFormat) {
        File file = new File(path);
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        if (file != null && file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, 100, fileOutputStream);
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片名称获取图片的资源id的方法
     *
     * @param
     * @return
     */
    public ArrayList<DrawableModel> getResourceByReflect() {
        Class drawable = R.drawable.class;
        ArrayList<DrawableModel> arrayList = new ArrayList();
        Field[] fields = drawable.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().startsWith("assets")) {
                int r_id;
                try {
                    r_id = field.getInt(field.getName());
                    DrawableModel model = new DrawableModel(r_id, field.getName());
                    arrayList.add(model);
                } catch (Exception e) {
                    Log.e("ERROR", "PICTURE NOT　FOUND！");
                }
            }
        }
        return arrayList;
    }

    public void requstPermission(String RemoteUrl) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        } else {
            downLoadBundle(RemoteUrl);
        }
    }



    private void downLoadBundle(String RemoteUrl) {
        registeReceiver();
        // 1.下载前检查SD卡是否存在更新包文件夹
        HotUpdate.checkPackage(getApplicationContext(), FileConstant.JS_BUNDLE_LOCAL_PATH);
        // 2.下载
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager
                .Request(Uri.parse(RemoteUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://" + FileConstant.JS_PATCH_LOCAL_PATH));
        mDownLoadId = downloadManager.enqueue(request);
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
                        List<DrawableModel> models = getResourceByReflect();
                        Log.e("show", "node_modes" + models.size() + "个图片");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (DrawableModel model : models) {
                                    Log.e("show", "id:" + model.getId() + "name:" + model.getName());
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), model.getId());
                                    if (bitmap != null) {
                                        saveBitMapToSdcard(bitmap, FileConstant.DRAWABLE_PATH, FileConstant.DRAWABLE_PATH + File.separator + model.getName() + ".png", Bitmap.CompressFormat.PNG);
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
                            Toast.makeText(this,"需要授权",Toast.LENGTH_SHORT).show();
                        }
                    }
                break;
        }
    }

    /**
     * 注册广播
     */
    private void registeReceiver() {
        localReceiver = new CompleteReceiver();
        registerReceiver(localReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public class CompleteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (completeId == mDownLoadId) {
                HotUpdate.handleZIP(getApplicationContext(), now_version, old_version, AllUpdate, handler);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(localReceiver!=null) {
            unregisterReceiver(localReceiver);
        }
    }
}
