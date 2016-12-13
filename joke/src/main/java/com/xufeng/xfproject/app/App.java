package com.xufeng.xfproject.app;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.xufeng.xfproject.base.BaseApp;

/**
 * Created by xufeng on 2016/12/1.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
