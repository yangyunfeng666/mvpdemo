package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.dragger.CarModule;
import com.yunsoft.mvpdemo.dragger.Engine;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

import javax.inject.Inject;
import javax.inject.Qualifier;

import dagger.Component;

/**
 * Author: yangyunfeng
 * Date: 公元2018-4-24 14:06
 * Description:this is DraggerActivity
 */


public class DraggerActivity extends BaseMvpActivity {

    private TextView textView;
    private Button button;

   @CarModule.QualifierA
   @Inject Engine mEngineA;
   @CarModule.QualifierB
   @Inject Engine mEngineB;


    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dragger);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        DaggerDraggerActivity_ActivityCommpont.builder().carModule(new CarModule("AAAA","BBBB")).build().inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textView.setText("A:" +mEngineA.run()+ "B:"+mEngineB.run());
            }
        });
    }

    @Override
    protected void initData() {

    }
    @Component(modules = {CarModule.class})
    public interface ActivityCommpont{
        void inject(DraggerActivity draggerActivity);
    }
}
