package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.yunsoft.mvpdemo.BuildConfig;
import com.yunsoft.reactnativeupdate.FileConstant;

import java.io.File;

/*
 * Author: yangyunfeng
 * Date: 公元2018-5-29 16:47
 * Description:this is ReactNativeActivity
*/

public class ReactNativeActivityBak extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private static final String TAG = "ReactNativeActivityBak";
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private ReactInstanceManagerBuilder builder;
    private String new_version = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new_version = getIntent().getStringExtra("version");
        mReactRootView = new ReactRootView(this);
        builder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED);

        File file = new File(FileConstant.getInstance().JS_BUNDLE_LOCAL_PATH+new_version+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        if (file != null && file.exists()) {
            Log.e("show", "load sdcard");
            builder.setJSBundleFile(FileConstant.getInstance().JS_BUNDLE_LOCAL_PATH+new_version+FileConstant.SPLEX+FileConstant.JS_BUNDLE_LOCAL_FILE);
        } else {
            Log.e("show", "load assess");
            builder.setBundleAssetName("index.android.bundle");
        }

        mReactInstanceManager = builder.build();
        mReactRootView.startReactApplication(mReactInstanceManager, "test", null);
        setContentView(mReactRootView);

    }


    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}
