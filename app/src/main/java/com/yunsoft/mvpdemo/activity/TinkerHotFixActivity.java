package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yunsoft.mvpdemo.R;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-13 11:46
 * Description:this is TinkerHotFixActivity
 */
public  class TinkerHotFixActivity extends AppCompatActivity {

    private EditText A_edit,B_edit;
    private TextView result_txt;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker_hot_fix);
        A_edit = findViewById(R.id.A_edit);
        B_edit = findViewById(R.id.B_edit);
        result_txt = findViewById(R.id.result_txt);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(A_edit.getText().toString().trim())&&!TextUtils.isEmpty(B_edit.getText().toString().trim())){
                    int a = Integer.parseInt(A_edit.getText().toString().trim());
                    int b = Integer.parseInt(B_edit.getText().toString().trim());
                    if(b==0){//这里是pat和非patch 文件的区别代码
                        Toast.makeText(TinkerHotFixActivity.this,"被除数不能为零",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result_txt.setText(a/b+"");
                }
            }
        });

    }
}
