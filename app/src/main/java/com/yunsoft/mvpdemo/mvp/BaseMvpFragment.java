package com.yunsoft.mvpdemo.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kye.basemodule.mvp.BaseUiInterface;
import com.kye.basemodule.view.CustomToast;
import com.yunsoft.mvpdemo.R;

/**
 * Created by yyf on 2018-04-17 11:31.
 */

public abstract class BaseMvpFragment extends BaseRxFragment implements BaseUiInterface {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);

    }

    abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    @Override
    public void showNetWorkException() {
        CustomToast.makeCustomText(getContext(),getResources().getString(R.string.msg_networkexcption), Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
    }

    @Override
    public void showUnKnownException() {
        CustomToast .makeCustomText(getContext(),getResources().getString(R.string.msg_unkownexcetion), Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
    }

    @Override
    public void showDataException(String msg) {
        CustomToast .makeCustomText(getContext(),msg, Toast.LENGTH_LONG,getResources().getColor(R.color.colorPrimary)).show();
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
