package com.yunsoft.mvpdemo.commponent.lifecycle.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource.Status.ERROR;
import static com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource.Status.LOADING;
import static com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource.Status.MORE_ADD;
import static com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource.Status.SUCCEED;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 16:29
 * Description:this is Resource
 * 读取的数据状态和返回数据类
 */

public class Resource<T> {
    public enum Status {
        LOADING, MORE_ADD, SUCCEED, ERROR
    }

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCEED, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public static <T> Resource<T> moreSucceed(@Nullable T data) {
        return new Resource<>(MORE_ADD, data, null);
    }

    public static <T> Resource<T> success(@NonNull T data, String msg) {
        return new Resource<>(SUCCEED, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data, String msg) {
        return new Resource<>(LOADING, data, msg);
    }

    public static <T> Resource<T> moreSucceed(@Nullable T data, String msg) {
        return new Resource<>(MORE_ADD, data, msg);
    }
}
