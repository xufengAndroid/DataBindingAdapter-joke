package com.xufeng.xfproject.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by xufeng on 2016/12/1.
 */

public class BaseApp extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
    }

    private void initContext() {
        if (null == mContext) {
            synchronized (BaseApp.class) {
                if (null == mContext) {
                    mContext = getApplicationContext();
                }
            }
        }
    }
}
