package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BaseView;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Created by yyf on 2018-04-11 16:57.
 */

public interface SimpleView extends BaseView {
   void loadDataSuccess(LocalUserInfo data);
}
