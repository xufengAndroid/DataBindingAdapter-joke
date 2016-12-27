package com.xufeng.xfproject.viewholder;

import android.content.Context;
import android.databinding.BindingConversion;
import android.databinding.ViewDataBinding;

import com.xufeng.databindingadapter.BindingViewHolder;
import com.xufeng.xfproject.BR;
import com.xufeng.xfproject.data.dto.JokeDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JokeViewHolder extends BindingViewHolder<JokeDto> {


    public JokeViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    protected void updateView(Context context, JokeDto object, int position) {
//        object.content = "内容："+ object.content;
        getBinding().setVariable(BR.joke, object);
    }

    @BindingConversion
    public static String convertDate(Date date) {
        if (null == date) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(date);

    }
}
