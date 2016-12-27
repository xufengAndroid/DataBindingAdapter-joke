package com.xufeng.xfproject.utils;

/**
 * Created by xufeng on 2016/12/19.
 */

public class DataTimeUtils {

    /**
     *
     * @return 10位时间戳
     */
    public static long getTimeStamp10(){
        long time = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
        return time;
    }
}
