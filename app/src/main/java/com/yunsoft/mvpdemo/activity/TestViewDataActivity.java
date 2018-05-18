package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.R;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-18 15:24
 * Description:this is ModelViewDataActivity
 */

public class TestViewDataActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_view_data);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.text);
    }
}
