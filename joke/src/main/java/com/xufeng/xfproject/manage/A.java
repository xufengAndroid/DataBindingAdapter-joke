package com.xufeng.xfproject.manage;

import com.xufeng.xfproject.data.repository.JokeRepository;

/**
 * Created by xufeng on 2016/12/6.
 */

public class A {

    public static JokeRepository getJokeRepository(){
        return HttpRetrofitManage.getRepository(JokeRepository.class);
    }

}
