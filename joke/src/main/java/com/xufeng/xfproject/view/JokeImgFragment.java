package com.xufeng.xfproject.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.common.base.Preconditions;
import com.xufeng.xfproject.BR;
import com.xufeng.xfproject.R;
import com.xufeng.xfproject.adapter.DividerItemDecoration;
import com.xufeng.xfproject.base.BaseFragment;
import com.xufeng.xfproject.contract.JokeContract;
import com.xufeng.xfproject.data.dto.JokeDto;
import com.xufeng.xfproject.databinding.JokeImgFragBinding;

import java.util.List;


/**
 * Created by xufeng on 2016/12/8.
 */

public class JokeImgFragment extends BaseFragment implements JokeContract.View {

    JokeContract.Presenter mPresenter;
    JokeImgFragBinding mBinding;
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
        mBinding = JokeImgFragBinding.inflate(inflater, container, false);
        mBinding.setPresenter(mPresenter);
        View root = mBinding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jokeAdapter = new JokeAdapter();
        mBinding.recJoke.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recJoke.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mBinding.recJoke.setAdapter(jokeAdapter);
    }

    @Override
    public void setPresenter(@NonNull JokeContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showFirstPage(int pageRows, int totalRows, List<JokeDto> list) {
        jokeAdapter.setData(list);
    }

    @Override
    public void showNextPage(int cruPage, int pageRows, int totalRows, List<JokeDto> list) {
        jokeAdapter.setData(list);
    }

    @Override
    public void showPageErrMsg(String code, String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    public static class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

        public List<JokeDto> contentlist;

        public JokeAdapter() {
        }

        public void setData(List<JokeDto> contentlist) {
            this.contentlist = contentlist;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.joke_img_item, parent, false);
            ViewHolder holder = new ViewHolder(viewDataBinding.getRoot());
            holder.setBinding(viewDataBinding);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getBinding().setVariable(BR.joke, contentlist.get(position));
            holder.getBinding().executePendingBindings();
        }

        @BindingAdapter({"url"})
        public static void setUrl(SimpleDraweeView view, String url) {
            view.setImageURI(url);
        }


        @Override
        public int getItemCount() {
            if (null != contentlist) {
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
