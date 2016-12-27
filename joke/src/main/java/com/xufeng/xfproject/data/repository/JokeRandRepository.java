package com.xufeng.xfproject.data.repository;

import com.xufeng.xfproject.data.resq.JokeRandResp;
import com.xufeng.xfproject.manage.HttpRetrofitManage;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xufeng on 2016/12/1.
 */

public interface JokeRandRepository {

    //服务器路径
    String API_SERVER = "http://v.juhe.cn/joke/";
    String APIKEY_VAL = "fab86b249c367a53a59e841f2e3ee42e";

    /**
     *
     * @param type 类型(pic:趣图,不传默认为笑话)
     * @return JokeResp;
     */
    //随机获取趣图/笑话 http://v.juhe.cn/joke/randJoke.php
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT})
    @GET("randJoke.php")
    Observable<JokeRandResp> getJokeRandJoke(@Query("type") String type, @Query("key") String key);

}
