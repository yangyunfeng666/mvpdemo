package com.kye.basemodule.mvp;

import android.app.Dialog;

/**
 * Created by yyf on 2018-04-11 12:14.
 * 基础的关于数据请求的UI显示
 */

public interface BaseView extends ILifecycleView {

    /**
     * 网络出错
     */
    void showNetWorkException();

    /**
     * 未知的错误
     */
    void showUnKnownException();

    /**
     * 数据转换出错
     */
    void showDataException(String e);

    /**
     * 网络请求完毕
     */
    void showLoadDataComplete();

    /**
     * 显示进度条对话框。
     */
    Dialog showLoadingDialog();

    /**
     * 关闭进度条对话框。
     */
    void dismissLoadingDialog();

}
