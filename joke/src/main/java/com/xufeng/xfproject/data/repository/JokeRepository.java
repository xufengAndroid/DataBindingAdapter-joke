package com.xufeng.xfproject.data.repository;

import com.xufeng.xfproject.data.resq.JokeResp;
import com.xufeng.xfproject.manage.HttpRetrofitManage;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xufeng on 2016/12/1.
 */

public interface JokeRepository {
    public static String BAIDU_APIKEY_NAME = "apikey:";
    public static String BAIDU_APIKEY_VAL = "fd74b539ad3eda7f28027aa694cdfad0";

    //段子列表接口
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT,BAIDU_APIKEY_NAME+BAIDU_APIKEY_VAL})
    @GET("showapi_joke/joke_text")
    Observable<JokeResp> getJokeList(@Query("page")int page);

    //图片段子列表接口
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT,BAIDU_APIKEY_NAME+BAIDU_APIKEY_VAL})
    @GET("showapi_joke/joke_pic")
    Observable<JokeResp> getJokeImgList(@Query("page")int page);

}
