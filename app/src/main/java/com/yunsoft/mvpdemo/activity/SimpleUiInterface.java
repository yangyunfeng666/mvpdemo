package com.yunsoft.mvpdemo.activity;

import com.kye.basemodule.mvp.BaseUiInterface;
import com.yunsoft.mvpdemo.data.LocalUserInfo;

/**
 * Created by yyf on 2018-04-11 16:57.
 */

public interface SimpleUiInterface extends BaseUiInterface {
   void loadDataSuccess(LocalUserInfo data);
}
