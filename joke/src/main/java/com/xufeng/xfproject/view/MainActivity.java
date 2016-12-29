package com.xufeng.xfproject.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.xufeng.xfproject.R;
import com.xufeng.xfproject.base.BaseActivity;
import com.xufeng.xfproject.databinding.MainActBinding;
import com.xufeng.xfproject.manage.A;
import com.xufeng.xfproject.presenter.JokeAllPresenter;
import com.xufeng.xfproject.presenter.JokeImgPresenter;
import com.xufeng.xfproject.presenter.JokePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xufeng on 2016/11/24.
 * 认清Android框架 MVC，MVP和MVVM
 * http://blog.csdn.net/jdsjlzx/article/details/51174396
 * google mvp
 * https://github.com/googlesamples/android-architecture
 * 依赖注入
 * http://www.open-open.com/lib/view/open1474442495481.html
 * http://blog.csdn.net/u014315849/article/details/51566388
 * databinding
 * http://blog.zhaiyifan.cn/2016/06/16/android-new-project-from-0-p7/
 * http://blog.csdn.net/marktheone/article/details/52044425
 * http://www.jianshu.com/p/eb29c691d370
 * API
 * http://apistore.baidu.com/apiworks/servicedetail/864.html
 * 上拉加载更多
 * http://blog.csdn.net/sbsujjbcy/article/details/50112391
 * http://www.jianshu.com/p/3bf125b4917d
 */
public class MainActivity extends BaseActivity {


    MainActBinding mainActBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActBinding = DataBindingUtil.setContentView(this, R.layout.main_act);

        mainActBinding.toolbar.inflateMenu(R.menu.drawer_actions);
        mainActBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                switch (menuItemId){
                    case R.id.menu_about:
                        startActivity(new Intent(getContext(),AboutActivity.class));
                        break;
                }
                return true;
            }
        });


        List<Fragment> list_fragment = new ArrayList<>();
        List<String> list_Title = new ArrayList<>();
        {
            JokeAllFragment fragment = new JokeAllFragment();
            fragment.setPresenter(new JokeAllPresenter(A.getJokeRandRepository(), fragment));
            list_fragment.add(fragment);
            list_Title.add("全部");
        }
        {
            JokeFragment fragment = new JokeFragment();
            fragment.setPresenter(new JokePresenter(A.getJokeRepository(), fragment));
            list_fragment.add(fragment);
            list_Title.add("段子");
        }
        {
            JokeImgFragment fragment = new JokeImgFragment();
            fragment.setPresenter(new JokeImgPresenter(A.getJokeRepository(), fragment));
            list_fragment.add(fragment);
            list_Title.add("图片");
        }
        mainActBinding.vpJokePager.setAdapter(new JokeTabAdapter(getSupportFragmentManager(), list_fragment, list_Title));
        mainActBinding.tabJokeTitle.setupWithViewPager(mainActBinding.vpJokePager);

    }


    public class JokeTabAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;                         //fragment列表
        private List<String> list_Title;                              //tab名的列表

        public JokeTabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_Title.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position % list_Title.size());
        }
    }

    private long firstTime;
    @Override
    public void onBackPressed() {

        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Snackbar sb = Snackbar.make(getRootView(), "再按一次退出", Snackbar.LENGTH_SHORT);
            sb.getView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.accent));
            TextView textView = (TextView) sb.getView().findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.primary_text));
            sb.show();
            firstTime = secondTime;
        } else {
            finish();
        }
    }

}
