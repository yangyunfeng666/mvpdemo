package com.yunsoft.mvpdemo.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yunsoft.mvpdemo.MyApplication;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.db.LocalUser;
import com.yunsoft.mvpdemo.viewmodel.LocalUserViewModel;

/**
 * Author: yangyunfeng
 * Date: 公元2018-6-15 16:04
 * Description:this is PageActivity
 */

public class PageActivity extends AppCompatActivity {

    private RecyclerView recycle_view;
    private LocalUserViewModel localUserViewModel;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        recycle_view = findViewById(R.id.recycle_view);
        button = findViewById(R.id.button);
        UserAdapter adapter = new UserAdapter();
        //为LocalUserViewModel 提供构造参数
        localUserViewModel = ViewModelProviders.of(this,new ViewModelProvider.NewInstanceFactory(){
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(LocalUserViewModel.class)) {
                    return (T) new LocalUserViewModel(((MyApplication) getApplication()).getDataBase().LocalUserDao());
                }

                throw new IllegalArgumentException("not instance ");
            }
        }).get(LocalUserViewModel.class);
        recycle_view.setAdapter(adapter);
        //localUserViewModel的数据发生改变，更新adapter
        localUserViewModel.pagedListLiveData.observe(this,pageList->adapter.submitList(pageList));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Math.max(0,5);
                LocalUser localUser = new LocalUser.Builder().age(age).sex("男").username("杨").build();
                ( (MyApplication)getApplication()).getDataBase().LocalUserDao().insert(localUser);
            }
        });
    }
    //不同数据的区分
    public  final   DiffUtil.ItemCallback<LocalUser> Differ = new DiffUtil.ItemCallback<LocalUser>() {
        @Override
        public boolean areItemsTheSame(LocalUser oldItem, LocalUser newItem) {
            //如果数据库数据方式改变的，同步数据的依据是透明的ID相同
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(LocalUser oldItem, LocalUser newItem) {
            //如果他们的内容是否相等，你需要重写object的equals
            return oldItem.equals(newItem);
        }
    };



    public class UserAdapter extends PagedListAdapter<LocalUser,UserViewHolder> {

        public UserAdapter() {
            super(Differ);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getItemCount() {
            return super.getItemCount();
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_page_list,parent,false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            LocalUser user =getItem(position);
            if(user!=null){
                holder.bindTo(user);
            }else{
                holder.clear();
            }
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }

        public void bindTo(LocalUser localUser){
            if(localUser!=null&&localUser.getUsername()!=null){
                name.setText(localUser.getUsername());
            }
        }

        public void clear(){

        }
    }

}
