package com.yunsoft.mvpdemo.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.kye.basemodule.mvp.BaseView;
import com.kye.basemodule.view.CustomToast;
import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by yyf on 2018-04-11 16:02.
 * 基础模板代码，可以根据自己的项目自由添加
 */

public abstract class BaseMvpActivity extends BaseRxAppCompatActivity implements BaseView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCreateBefore();
        //判断在DispatchingAndroidInjector 里面是否有改Activity的注入，如果有，则注入改Activity
        if(((DispatchingAndroidInjector<Activity>)((MyApplication)getApplication()).activityInjector()).maybeInject(this))
        {
            AndroidInjection.inject(this);
        }
        super.onCreate(savedInstanceState);
        initViews(savedInstanceState);
        initData();
    }

    /**
     * 在oncreate方法前的调用
     */
    protected  abstract void  onCreateBefore();

    /**
     * 页面初始化
     * @param savedInstanceState
     */
    protected  abstract void  initViews(Bundle savedInstanceState);
    /**
     * 一些数据加载操作
     */
    protected  abstract void  initData();

    @Override
    public void showNetWorkException() {
        CustomToast .makeCustomText(this,getResources().getString(R.string.msg_networkexcption), Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
    }

    @Override
    public void showUnKnownException() {
        CustomToast .makeCustomText(this,getResources().getString(R.string.msg_unkownexcetion), Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
    }

    @Override
    public void showDataException(String msg) {
        CustomToast .makeCustomText(this,msg, Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
    }

    /**
     * 这里根据自己今天情况定义
     */
    @Override
    public void showLoadDataComplete() {

    }


    @Override
    public Dialog showLoadingDialog() {
        return null;
    }

    @Override
    public void dismissLoadingDialog() {

    }

}
