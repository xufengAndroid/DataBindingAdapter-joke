package com.xufeng.xfproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by xufeng on 2016/12/5.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View getRootView(){
        return findViewById(android.R.id.content);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        ButterKnife.bind(this);
        onViewCreated(getRootView(),getIntent().getExtras());
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    public Context getContext(){
        return this;
    }

}
