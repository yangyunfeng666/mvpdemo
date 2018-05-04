package com.yunsoft.mvpdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kye.basemodule.view.CLipeListView;
import com.yunsoft.mvpdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyf on 2018/5/3 12:26
 */

public class MyListViewActivity extends Activity {

    private CLipeListView listview;
    private MyAdapter adapter;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listview);
        for(int i=0;i<10;i++){
            datas.add("datas"+i);
        }
        listview = findViewById(R.id.listview);
        adapter = new MyAdapter(datas,this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyListViewActivity.this,datas.get(i),Toast.LENGTH_SHORT).show();
            }
        });
        listview.setDeleteListener(new CLipeListView.DeleteListener() {
            @Override
            public void DeleteItem(int position) {
                Log.e("show","DeleteItem:"+position);
                datas.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private static class MyAdapter extends BaseAdapter{

        private List<String> data;
        private Context context;

        interface OnItemClickListener{
          void  onClick(int position);
        }



        public MyAdapter(List<String> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.adapter_my_list,viewGroup,false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
              }else{
                viewHolder = (ViewHolder) view.getTag();
            }



            viewHolder.textView.setText(data.get(i));
            return view;
        }

        class ViewHolder{
            private TextView textView;
            ViewHolder(View view){
               this.textView  = view. findViewById(R.id.text);
            }
        }
    }
}
