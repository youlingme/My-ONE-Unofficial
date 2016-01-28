package com.youlingme.my_one_unofficial.common;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.youlingme.my_one_unofficial.utils.ImageUtil;
import com.youlingme.my_one_unofficial.utils.NetworkUtil;

/**
 * User: youlingme
 * Date: 2016-01-28 13:35
 * 整个App需要配置的代码都在这里配置
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        NetworkUtil.init(this);
        ImageUtil.init(this);

    }
}
