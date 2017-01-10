/*
 * Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xufeng.databindingadapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;


public abstract class BindingViewHolder<T> extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;
    private WeakReference<BindingAdapter<T>> mAdapterRef;

    private ViewHolderClickListener mViewHolderClickListener;
    private ViewHolderLongClickListener mViewHolderLongClickListener;

    private int mLastBindPosition = -1;

    private T mObject;

    public BindingViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        mBinding = viewDataBinding;
    }

    void onBindView(T object, int position) {
        mObject = object;
        mLastBindPosition = position;
        updateView(getView().getContext(), mObject, position);
    }

    protected abstract void updateView(Context context, T object, int position);

    public <V extends ViewDataBinding> V getBinding() {
        return (V) mBinding;
    }

    void setAdapter(BindingAdapter<T> adapter) {
        if (adapter != getAdapter()) {
            mAdapterRef = new WeakReference<>(adapter);
        }
    }

    private BindingAdapter<T> getAdapter() {
        return mAdapterRef == null ? null : mAdapterRef.get();
    }

    public View getView() {
        return mBinding.getRoot();
    }

    View.OnClickListener getOnClickListener(boolean adapterHasListener) {
        if (isClickable() && adapterHasListener) {
            if (mViewHolderClickListener == null) {
                mViewHolderClickListener = new ViewHolderClickListener<>(this);
            }
        } else {
            mViewHolderClickListener = null;
        }
        return mViewHolderClickListener;
    }


    View.OnLongClickListener getOnLongClickListener(boolean adapterHasListener) {
        if (isLongClickable() && adapterHasListener) {
            if (mViewHolderLongClickListener == null) {
                mViewHolderLongClickListener = new ViewHolderLongClickListener<>(this);
            }
        } else {
            mViewHolderLongClickListener = null;
        }
        return mViewHolderLongClickListener;
    }

    public boolean isClickable() {
        return true;
    }

    public boolean isLongClickable() {
        return true;
    }

    public T getObject() {
        return mObject;
    }

    public int getLastBindPosition() {
        int adapterPosition = getAdapterPosition();
        if (adapterPosition == -1) {
            return mLastBindPosition;
        } else {
            return adapterPosition;
        }
    }

    private static class ViewHolderClickListener<T> implements View.OnClickListener {

        private WeakReference<BindingViewHolder<T>> mViewHolderRef;

        public ViewHolderClickListener(BindingViewHolder<T> viewHolder) {
            mViewHolderRef = new WeakReference<>(viewHolder);
        }

        @Override
        public void onClick(View v) {
            BindingViewHolder<T> viewHolder = mViewHolderRef.get();
            if (viewHolder == null) {
                return;
            }
            BindingAdapter<T> adapter = viewHolder.getAdapter();
            if (adapter == null) {
                return;
            }
            BindingAdapter.OnItemClickListener<T> listener = adapter.getOnItemClickListener();
            if (listener == null) {
                return;
            }
            T object = viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            listener.onItemClick(adapter, view, object, position);
        }
    }

    private static class ViewHolderLongClickListener<T> implements View.OnLongClickListener {

        private WeakReference<BindingViewHolder<T>> mViewHolderRef;

        public ViewHolderLongClickListener(BindingViewHolder<T> viewHolder) {
            mViewHolderRef = new WeakReference<>(viewHolder);
        }

        @Override
        public boolean onLongClick(View v) {
            BindingViewHolder<T> viewHolder = mViewHolderRef.get();
            if (viewHolder == null) {
                return false;
            }
            BindingAdapter<T> adapter = viewHolder.getAdapter();
            if (adapter == null) {
                return false;
            }
            BindingAdapter.OnItemLongClickListener<T> listener = adapter.getOnItemLongClickListener();
            if (listener == null) {
                return false;
            }
            T object = viewHolder.getObject();
            View view = viewHolder.getView();
            int position = viewHolder.getLastBindPosition();
            listener.onLongItemClick(adapter, view, object, position);
            return true;
        }
    }
}