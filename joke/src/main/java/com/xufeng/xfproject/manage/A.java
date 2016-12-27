package com.xufeng.xfproject.manage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xufeng.xfproject.data.repository.JokeRandRepository;
import com.xufeng.xfproject.data.repository.JokeRepository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xufeng on 2016/12/6.
 */

public class A {

    private static final HttpRetrofitManage hrm = new HttpRetrofitManage();

    private static JokeRepository mJokeRepository;

    private static JokeRandRepository mJokeRandRepository;


    public static JokeRepository getJokeRepository() {
        if (null == mJokeRepository) {
            synchronized (A.class) {
                if (null == mJokeRepository) {
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();//使用 gson coverter，统一日期请求格式
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(JokeRepository.API_SERVER)
                            .client(hrm.getOkHttpClient())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    mJokeRepository = retrofit.create(JokeRepository.class);
                }
            }
        }
        return mJokeRepository;
    }

    public static JokeRandRepository getJokeRandRepository() {
        if (null == mJokeRandRepository) {
            synchronized (A.class) {
                if (null == mJokeRandRepository) {
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();//使用 gson coverter，统一日期请求格式
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(JokeRandRepository.API_SERVER)
                            .client(hrm.getOkHttpClient())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    mJokeRandRepository = retrofit.create(JokeRandRepository.class);
                }
            }
        }
        return mJokeRandRepository;
    }
}
