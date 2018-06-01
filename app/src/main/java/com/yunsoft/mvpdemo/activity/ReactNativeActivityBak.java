package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.facebook.react.JSCConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.yunsoft.mvpdemo.reactnative.FileConstant;
import com.yunsoft.reactnative.BuildConfig;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.yunsoft.mvpdemo.reactnative.FileConstant.JS_BUNDLE_LOCAL_PATH;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-29 16:47
 * Description:this is ReactNativeActivity
 */
public class ReactNativeActivityBak extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private static final String TAG = "ReactNativeActivityBak";
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private ReactInstanceManagerBuilder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "dddd", Toast.LENGTH_SHORT).show();
        mReactRootView = new ReactRootView(this);
        builder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED);
        File file = new File(FileConstant.JS_BUNDLE_LOCAL_PATH);
        if (file != null && file.exists()) {
            Log.e("show", "load sdcard");
            builder.setJSBundleFile(FileConstant.JS_BUNDLE_LOCAL_PATH);
        } else {
            Log.e("show", "load assess");
            builder.setBundleAssetName("index.android.bundle");
        }
        mReactInstanceManager = builder.build();
        mReactRootView.startReactApplication(mReactInstanceManager, "test", null);
        setContentView(mReactRootView);
    }

    /**
     * 反射替换jsbunder
     */
    private void onJSBundleLoadedFromServer() {
        File file = new File(FileConstant.JS_BUNDLE_LOCAL_PATH);
        if (file == null || !file.exists()) {
            Log.i(TAG, "js bundle file download error, check URL or network state");
            return;
        }
        Log.i(TAG, "js bundle file file success, reload js bundle");
        Toast.makeText(ReactNativeActivityBak.this, "download bundle complete", Toast.LENGTH_SHORT).show();
        try {
            Class<?> RIManagerClazz = mReactInstanceManager.getClass();
            Field f = RIManagerClazz.getDeclaredField("mJSCConfig");
            f.setAccessible(true);
            JSCConfig jscConfig = (JSCConfig) f.get(mReactInstanceManager);
            Method method = RIManagerClazz.getDeclaredMethod("recreateReactContextInBackground",
                    com.facebook.react.bridge.JSCJavaScriptExecutor.Factory.class,
                    com.facebook.react.bridge.JSBundleLoader.class);
            method.setAccessible(true);
            method.invoke(mReactInstanceManager,
                    new com.facebook.react.bridge.JSCJavaScriptExecutor.Factory(jscConfig.getConfigMap()),
                    com.facebook.react.bridge.JSBundleLoader.createFileLoader(FileConstant.JS_BUNDLE_LOCAL_PATH));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
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
