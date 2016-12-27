package com.xufeng.databindingadapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class BindingAdapterHelper<T> {

    private final Class<? extends BindingViewHolder<? extends T>> mViewHolderClass;

    private final int mLayoutResId;

    private final List<T> mObjects;

    private BindingAdapter.OnItemClickListener<T> mOnItemClickListener;

    private BindingAdapter.OnItemLongClickListener<T> mOnItemLongClickListener;


    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    BindingAdapterHelper(T... objects) {
        this(new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param objects The objects to represent in the RecyclerView.
     */
    BindingAdapterHelper(List<T> objects) {
        this(0,null,objects);
    }

    /**
     * Constructor
     *
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param objects     the objects to put in this adapter
     */
    BindingAdapterHelper(int layoutResId, Class<? extends BindingViewHolder<? extends T>> viewHolderClass, T... objects) {
        this(layoutResId,viewHolderClass,new ArrayList<>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     *
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param objects     the objects to put in this adapter
     */
    BindingAdapterHelper(int layoutResId,Class<? extends BindingViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        if (objects == null) {
            mObjects = new ArrayList<>();
        } else {
            mObjects = objects;
        }
        mViewHolderClass = viewHolderClass;
        mLayoutResId = layoutResId;
    }


    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been long-clicked.
     * <p/>
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemLongClickListener(BindingAdapter.OnItemLongClickListener<T> listener) {
        mOnItemLongClickListener = listener;
    }

    /**
     * Register a callback to be invoked when an item in this AbsViewHolderAdapter has
     * been clicked.
     * <p/>
     * Your view holder must allow the click by overriding the method "isClickable()"
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemClickListener(BindingAdapter.OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    /**
     * Get the callback to be invoked when an item in this adapter has been clicked.
     *
     * @return listener The callback that will be invoked on item click.
     */
    public BindingAdapter.OnItemClickListener<T> getOnItemClickListener() {
        return mOnItemClickListener;
    }

    /**
     * Get the callback to be invoked when an item in this adapter has been long-clicked.
     *
     * @return listener The callback that will be invoked on item long click.
     */
    public BindingAdapter.OnItemLongClickListener<T> getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    /**
     * Determine if the object provide is in this adapter
     *
     * @return true if the object is in this adapter
     */
    boolean hasItem(T object) {
        return mObjects.contains(object);
    }

    /**
     * Searches this {@code List} for the specified object and returns the index of the
     * first occurrence.
     *
     * @param object the object to search for.
     * @return the index of the first occurrence of the object or -1 if the
     * object was not found.
     */
    int indexOf(T object) {
        return mObjects.indexOf(object);
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    int addAll(@NonNull Collection<? extends T> collection) {
        int positionOfInsert = mObjects.size();
         mObjects.addAll(collection);
        return positionOfInsert;
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    int addAll(T... items) {
        int positionOfInsert = mObjects.size();
        Collections.addAll(mObjects, items);
        return positionOfInsert;
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    int add(T object) {
        int positionOfInsert = mObjects.size();
        mObjects.add(object);
        return positionOfInsert;
    }

    /**
     * Adds the specified object at the specified position of the array.
     *
     * @param position The position of object to add
     * @param object   The object to add at the end of the array.
     */
    int add(int position, T object) {
        mObjects.add(position, object);
        return position;
    }


    /**
     * Remove the object at the specified position of the array.
     *
     * @param position The position of object to add
     */
    T removeAt(int position) {
        return mObjects.remove(position);
    }


    /**
     * Remove the specified object of the array.
     *
     * @param object The object to add at the end of the array.
     */
    int remove(T object) {
        int positionOfRemove = mObjects.indexOf(object);
        if (positionOfRemove >= 0) {
            T objectRemoved = removeAt(positionOfRemove);
            if (objectRemoved != null) {
                return positionOfRemove;
            }
        }
        return -1;
    }

    /**
     * Move object
     */
    void move(int from, int to) {
        mObjects.add(to, mObjects.remove(from));
    }

    /**
     * Remove all elements from the list.
     */
    int clear() {
        int nbObjectRemoved = mObjects.size();
        mObjects.clear();
        return nbObjectRemoved;
    }

    void resetAll(Collection<? extends T> collection){
        mObjects.clear();
        if(null!=collection){
            mObjects.addAll(collection);
        }
    }


    /**
     * Returns whether this {@code List} contains no elements.
     *
     * @return {@code true} if this {@code List} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this {@code List}.
     *
     * @return the number of elements in this {@code List}.
     */
    int size() {
        return mObjects.size();
    }

    /**
     * @return a copy of the {@code List} of elements.
     */
    List<T> getObjects() {
        return new ArrayList<>(mObjects);
    }

    T get(int position) {
        return mObjects.get(position);
    }


    ViewDataBinding inflateView(ViewGroup parent, int layoutResId) {
        if (parent == null) {
            return null;
        }
        Context context = parent.getContext();
        if (context == null) {
            return null;
        }
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
        return viewDataBinding;
    }

    int getLayoutResId() {
        return mLayoutResId;
    }


    void onBindViewHolder(BindingViewHolder<T> holder, int position,BindingAdapter<T> adapter) {
        T object = mObjects.get(position);
        holder.onBindView(object, position);
        holder.setAdapter(adapter);
        setClickListenerOnView(holder);
        setLongClickListenerOnView(holder);
    }


    Class<? extends BindingViewHolder<? extends T>> getViewHolderClass() {
        return mViewHolderClass;
    }

    private void setClickListenerOnView(BindingViewHolder viewHolder) {
        View view = viewHolder.getView();
        View.OnClickListener listener = viewHolder.getOnClickListener(mOnItemClickListener != null);
        view.setOnClickListener(listener);
        if (listener == null) {
            view.setClickable(false);
        }
    }

    private void setLongClickListenerOnView(BindingViewHolder viewHolder) {
        View view = viewHolder.getView();
        View.OnLongClickListener listener = viewHolder.getOnLongClickListener(mOnItemClickListener != null);
        view.setOnLongClickListener(listener);
        if (listener == null) {
            view.setLongClickable(false);
        }
    }


    BindingViewHolder<T> generateViewHolder(ViewDataBinding viewDataBinding,
                                              Class<? extends BindingViewHolder<? extends T>> viewHolderClass,
                                            BindingAdapter<T> adapter) {
        Constructor<?>[] constructors = viewHolderClass.getDeclaredConstructors();

        if (constructors == null) {
            throw new IllegalArgumentException(
                    "Impossible to found a constructor for " + viewHolderClass.getSimpleName());
        }

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes == null) {
                continue;
            }

            try {
                Object viewHolder;
                if (isAssignableFrom(parameterTypes, View.class)) {
                    //single or static inner class ViewHolder
                    viewHolder = constructor.newInstance(viewDataBinding.getRoot());
                } else if (isAssignableFrom(parameterTypes,BindingAdapter.class, View.class)) {
                    // inner class ViewHolder inside EfficientAdapter
                    viewHolder = constructor.newInstance(adapter, viewDataBinding.getRoot());
                } else if (isAssignableFrom(parameterTypes,ViewDataBinding.class)) {
                    // inner class ViewHolder inside EfficientAdapter
                    viewHolder = constructor.newInstance(viewDataBinding);
                }else {
                    viewHolder = null;
                }
                if (viewHolder instanceof BindingViewHolder) {
                    return (BindingViewHolder<T>) viewHolder;
                }
            } catch (Exception e) {
                throw new RuntimeException(
                        "Impossible to instantiate " + viewHolderClass.getSimpleName(), e);
            }
        }
        throw new IllegalArgumentException(
                "Impossible to found a constructor with a view for "
                        + viewHolderClass.getSimpleName());
    }

    private static boolean isAssignableFrom(Class<?>[] parameterTypes, Class<?>... classes) {
        if (parameterTypes == null || classes == null || parameterTypes.length != classes.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!classes[i].isAssignableFrom(parameterTypes[i])) {
                return false;
            }
        }
        return true;
    }

    void throwMissingLayoutResId(int viewType) {
        throw new IllegalArgumentException("No default layout found for the view type '"
                + viewType + "'.");
    }

    void throwMissingViewHolder(int viewType) {
        throw new IllegalArgumentException(
                "You must supply a view holder class for the element for view type "
                        + viewType);
    }


}