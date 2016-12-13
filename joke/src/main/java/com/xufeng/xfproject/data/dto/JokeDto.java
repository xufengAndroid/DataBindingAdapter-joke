package com.xufeng.xfproject.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by xufeng on 2016/12/2.
 */

public class JokeDto extends BaseDto {
    @Expose
    @SerializedName("ct")
    public String ct;
    @Expose
    @SerializedName("text")
    public String text;
    @Expose
    @SerializedName("title")
    public String title;
    @Expose
    @SerializedName("type")
    public int type;
    @Expose
    @SerializedName("img")
    public String img;
}
