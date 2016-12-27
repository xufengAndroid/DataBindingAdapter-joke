package com.xufeng.databindingadapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by xufeng on 2016/12/14.
 */

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> implements BindingAdapter<T> {

    private final BindingAdapterHelper<T> mBaseAdapterHelper;

    private boolean mNotifyOnChange = true;
    public BindingRecyclerViewAdapter() {
        this(new ArrayList<T>());
    }
    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public BindingRecyclerViewAdapter(T[] objects) {
        this(new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects the objects to put in this adapter
     */
    public BindingRecyclerViewAdapter(List<T> objects) {
        this(0, null, objects);
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    public BindingRecyclerViewAdapter(int layoutResId,
                                    Class<? extends BindingViewHolder<? extends T>> viewHolderClass, T... objects) {
        this(layoutResId, viewHolderClass, new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param layoutResId     layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects         the objects to put in this adapter
     */
    public BindingRecyclerViewAdapter(int layoutResId, Class<? extends BindingViewHolder<? extends T>> viewHolderClass,List<T> objects) {
        mBaseAdapterHelper = new BindingAdapterHelper<>(layoutResId,viewHolderClass,objects);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = generateView(parent, viewType);
        return generateViewHolder(viewDataBinding,viewType);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        mBaseAdapterHelper.onBindViewHolder(holder,position,this);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public ViewDataBinding generateView(ViewGroup parent, int viewType) {
        int layoutResId = getLayoutResId(viewType);
        if (layoutResId == 0) {
            mBaseAdapterHelper.throwMissingLayoutResId(viewType);
            return null;
        }
        return mBaseAdapterHelper.inflateView(parent, layoutResId);
    }

    @Override
    public int getLayoutResId(int viewType) {
        // default implementation doesn't depend on 'viewType', we take the layout res id from
        // the constructor
        return mBaseAdapterHelper.getLayoutResId();
    }


    @Override
    public int getItemCount() {
        return size();
    }

    @Override
    public BindingViewHolder<T> generateViewHolder(ViewDataBinding viewDataBinding, int viewType) {
        Class<? extends BindingViewHolder<? extends T>> viewHolderClass = getViewHolderClass(viewType);
        if (viewHolderClass == null) {
            mBaseAdapterHelper.throwMissingViewHolder(viewType);
            return null;
        }
        return mBaseAdapterHelper.generateViewHolder(viewDataBinding,viewHolderClass, this);
    }

    public void setNotifyOnChange(boolean enable) {
        mNotifyOnChange = enable;
    }


    @Override
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mBaseAdapterHelper.setOnItemClickListener(listener);
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mBaseAdapterHelper.setOnItemLongClickListener(listener);
    }

    @Override
    public OnItemClickListener<T> getOnItemClickListener() {
        return mBaseAdapterHelper.getOnItemClickListener();
    }

    @Override
    public OnItemLongClickListener<T> getOnItemLongClickListener() {
        return mBaseAdapterHelper.getOnItemLongClickListener();
    }

    @Override
    public boolean hasItem(T object) {
        return mBaseAdapterHelper.hasItem(object);
    }

    @Override
    public int indexOf(T object) {
        return mBaseAdapterHelper.indexOf(object);
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        int positionOfInsert = mBaseAdapterHelper.addAll(collection);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void addAll(T... items) {
        int positionOfInsert = mBaseAdapterHelper.addAll(items);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void add(T object) {
        int positionOfInsert = mBaseAdapterHelper.add(object);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void add(int index, T object) {
        int positionOfInsert = mBaseAdapterHelper.add(index,object);
        if (mNotifyOnChange) {
            notifyItemInserted(positionOfInsert);
        }
    }

    @Override
    public void removeAt(int position) {
        T removeAt = mBaseAdapterHelper.removeAt(position);
        if (mNotifyOnChange && removeAt != null) {
            notifyItemRemoved(position);
        }
    }

    @Override
    public void remove(T object) {
        int positionOfRemove = mBaseAdapterHelper.remove(object);
        if (mNotifyOnChange && positionOfRemove >= 0) {
            notifyItemRemoved(positionOfRemove);
        }
    }

    @Override
    public void move(int from, int to) {
         mBaseAdapterHelper.move(from,to);
        notifyItemMoved(from, to);
    }

    @Override
    public void clear() {
        int nbObjectRemoved = mBaseAdapterHelper.clear();
        if (mNotifyOnChange) {
            for (int i = nbObjectRemoved - 1; i >= 0; i--) {
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public void resetAll(Collection<? extends T> collection) {
        mBaseAdapterHelper.resetAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mBaseAdapterHelper.isEmpty();
    }

    @Override
    public int size() {
        return mBaseAdapterHelper.size();
    }

    @Override
    public List<T> getObjects() {
        return mBaseAdapterHelper.getObjects();
    }

    @Override
    public T get(int location) {
        return mBaseAdapterHelper.get(location);
    }


    @Override
    public Class<? extends BindingViewHolder<? extends T>> getViewHolderClass(int viewType) {
        // default implementation doesn't depend on 'viewType', we take the viewholder class from
        // the constructor
        return mBaseAdapterHelper.getViewHolderClass();
    }



}
