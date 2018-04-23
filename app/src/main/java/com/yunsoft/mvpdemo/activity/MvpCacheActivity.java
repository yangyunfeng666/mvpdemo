package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-20 17:16
 * Description:this is MvpCacheActivity
 */


public class MvpCacheActivity  extends BaseMvpActivity implements MvpCacheContract.View{


    private TextView textView;
    private Button button;

    private MvpCachePresenter mvpCachePresenter;

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mvpcache);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        mvpCachePresenter = new MvpCachePresenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpCachePresenter.getPhoneLogin("1326579778","ab244795339868d6e9d35ed7e7de7e3b","104.22","12.2","31231213233",true);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loadDataSucess(LocalUserInfo userInfo) {
        textView.setText(userInfo.toString());
    }
}
