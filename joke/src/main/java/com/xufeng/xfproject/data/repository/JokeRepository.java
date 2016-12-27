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

    //服务器路径
    String API_SERVER = "http://japi.juhe.cn/joke/";
    String APIKEY_VAL = "fab86b249c367a53a59e841f2e3ee42e";

    //按更新时间查询笑话 http://japi.juhe.cn/joke/content/list.from
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT})
    @GET("content/list.from")
    Observable<JokeResp> getJokeContentList(@Query("sort")String sort,@Query("page")int page,@Query("pagesize")int pagesize,@Query("time")long time,@Query("key")String key);

    //最新笑话 http://japi.juhe.cn/joke/content/text.from
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT})
    @GET("content/text.from")
    Observable<JokeResp> getJokeContentText(@Query("page")int page,@Query("pagesize")int pagesize,@Query("key")String key);

    //按更新时间查询趣图 http://japi.juhe.cn/joke/img/list.from
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT})
    @GET("img/list.from")
    Observable<JokeResp> getJokeImgList(@Query("sort")String sort,@Query("page")int page,@Query("pagesize")int pagesize,@Query("time")long time,@Query("key")String key);

    //最新趣图 http://japi.juhe.cn/joke/img/text.from
    @Headers(value = {HttpRetrofitManage.CACHE_CONTROL_AGE + HttpRetrofitManage.CACHE_STALE_SHORT})
    @GET("img/text.from")
    Observable<JokeResp> getJokeImgText(@Query("page")int page,@Query("pagesize")int pagesize,@Query("key")String key);



}
