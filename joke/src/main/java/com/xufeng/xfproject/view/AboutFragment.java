package com.xufeng.xfproject.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xufeng.xfproject.BR;
import com.xufeng.xfproject.R;
import com.xufeng.xfproject.base.BaseFragment;

/**
 * Created by xufeng on 2016/12/28.
 */

public class AboutFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.about_frag, container, false);
        inflate.setVariable(BR.url,"res://com.xufeng.xfproject/"+R.drawable.ic_joke);
        inflate.setVariable(BR.webUrl,"https://github.com/xufengAndroid/XfProject");
        return inflate.getRoot();
    }


}
