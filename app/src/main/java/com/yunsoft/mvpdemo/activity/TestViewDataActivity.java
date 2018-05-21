package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kye.basemodule.mvp.BaseObserver;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.commponent.lifecycle.TestViewModel;
import com.yunsoft.mvpdemo.commponent.lifecycle.ViewModelFactory;
import com.yunsoft.mvpdemo.commponent.lifecycle.data.Resource;
import com.yunsoft.mvpdemo.data.LocalUserInfo;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 15:24
 * Description:this is ModelViewDataActivity
 */

public class TestViewDataActivity extends BaseMvpActivity {

    private Button button;

    private TextView textView;

    private TestViewModel testViewModel;

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_model_view_data);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.text);
        testViewModel = getViewModel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {
        testViewModel.loadData().observe(this, new Observer<Resource<LocalUserInfo>>() {
            @Override
            public void onChanged(@Nullable Resource<LocalUserInfo> localUserInfoResource) {
//                if (localUserInfoResource != null && localUserInfoResource.data != null) {
                    if (localUserInfoResource.status ==Resource.Status.LOADING) {
                        textView.setText("加载数据");
                    } else if (localUserInfoResource.status==Resource.Status.SUCCEED) {
                        textView.setText("加载数据成功");
                        if (localUserInfoResource.data != null) {
                            textView.setText(localUserInfoResource.data.toString());
                        }
                    } else if (localUserInfoResource.status==Resource.Status.ERROR) {
                        textView.setText(localUserInfoResource.message);
                    }
//                }
            }
        });
    }

    public  TestViewModel getViewModel(){
        ViewModelFactory viewModelFactory =  ViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this,viewModelFactory).get(TestViewModel.class);
    }


}
