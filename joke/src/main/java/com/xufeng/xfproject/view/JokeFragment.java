package com.xufeng.xfproject.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.common.base.Preconditions;
import com.xufeng.xfproject.BR;
import com.xufeng.xfproject.R;
import com.xufeng.xfproject.adapter.DividerItemDecoration;
import com.xufeng.xfproject.base.BaseFragment;
import com.xufeng.xfproject.contract.JokeContract;
import com.xufeng.xfproject.data.dto.JokeDto;
import com.xufeng.xfproject.databinding.JokeFragBinding;

import java.util.List;


/**
 * Created by xufeng on 2016/12/8.
 */

public class JokeFragment extends BaseFragment implements JokeContract.View{

    JokeContract.Presenter mPresenter;
    JokeFragBinding jokeFragBinding;
    JokeAdapter jokeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        jokeFragBinding = JokeFragBinding.inflate(inflater,container,false);
        jokeFragBinding.setPresenter(mPresenter);
        View root = jokeFragBinding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jokeAdapter = new JokeAdapter();
        jokeFragBinding.recJoke.setLayoutManager(new LinearLayoutManager(getContext()));
        jokeFragBinding.recJoke.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        jokeFragBinding.recJoke.setAdapter(jokeAdapter);
        jokeFragBinding.srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFirstPage();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull JokeContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showFirstPage(int pageRows, int totalRows, List<JokeDto> list) {
        jokeAdapter.setData(list);
        jokeFragBinding.srl.setRefreshing(false);
    }

    @Override
    public void showNextPage(int cruPage, int pageRows, int totalRows, List<JokeDto> list) {
        jokeAdapter.setData(list);
    }

    @Override
    public void showPageErrMsg(String code, String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>{

        // 设置底部布局
        private static final int TYPE_FOOTER = 0;
        // 设置默认布局
        private static final int TYPE_DEFAULT = 1;
        // 上下文
        private Context mContext;
        // 判断是不是最后一个item，默认是true
        private boolean mShowFooter = true;

        public List<JokeDto> contentlist;

        public JokeAdapter(){
        }

        public void setData(List<JokeDto> contentlist){
            this.contentlist = contentlist;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.joke_item,parent,false);
            ViewHolder holder = new ViewHolder(viewDataBinding.getRoot());
            holder.setBinding(viewDataBinding);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getBinding().setVariable(BR.joke, contentlist.get(position));
            holder.getBinding().executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if(null!=contentlist){
                return contentlist.size();
            }
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ViewDataBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
            }

            public void setBinding(ViewDataBinding binding) {
                this.binding = binding;
            }

            public ViewDataBinding getBinding() {
                return this.binding;
            }
        }

    }

}
