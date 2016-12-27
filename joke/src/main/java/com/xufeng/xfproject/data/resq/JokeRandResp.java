package com.xufeng.xfproject.data.resq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xufeng.xfproject.data.dto.BaseDto;
import com.xufeng.xfproject.data.dto.JokeDto;

import java.util.List;


/**
 * Created by xufeng on 2016/12/2.
 */

public class JokeRandResp extends BaseDto {

    @Expose
    @SerializedName("result")
    public List<JokeDto> JokeList;
}
