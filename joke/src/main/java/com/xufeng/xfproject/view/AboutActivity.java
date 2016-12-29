package com.xufeng.xfproject.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xufeng.xfproject.BR;
import com.xufeng.xfproject.R;
import com.xufeng.xfproject.base.BaseActivity;
import com.xufeng.xfproject.utils.ActivityUtils;

/**
 * Created by xufeng on 2016/12/27.
 */

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.about_act);
        viewDataBinding.setVariable(BR.activity,this);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),AboutFragment.class, R.id.framelayout);
    }


}
