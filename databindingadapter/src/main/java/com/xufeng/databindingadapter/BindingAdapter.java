package com.xufeng.databindingadapter;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

public interface BindingAdapter<T> {

    /**
     * Register a callback to be invoked when an item in this adapter has been clicked.
     * An {@link BindingViewHolder} can disable the click on it by overriding the method
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemClickListener(BindingAdapter.OnItemClickListener<T> listener);

    /**
     * Register a callback to be invoked when an item in this adapter has been long-clicked.
     * An {@link BindingViewHolder} can disable the long-click on it by overriding the method
     *
     * @param listener The callback that will be invoked.
     */
    void setOnItemLongClickListener(BindingAdapter.OnItemLongClickListener<T> listener);

    /**
     * Get the callback to be invoked when an item in this adapter has been clicked.
     *
     * @return listener The callback that will be invoked on item click.
     */
    BindingAdapter.OnItemClickListener<T> getOnItemClickListener();

    /**
     * Get the callback to be invoked when an item in this adapter has been long-clicked.
     *
     * @return listener The callback that will be invoked on item long click.
     */
    BindingAdapter.OnItemLongClickListener<T> getOnItemLongClickListener();

    /**
     * Returns whether this {@code Adapter} contains this element.
     *
     * @param object the object to search for.
     * @return {@code true} if this {@code Adapter} has this elements, {@code false}
     * otherwise.
     */
    boolean hasItem(T object);

    /**
     * Searches this {@code Adapter} for the specified object and returns the index of the
     * first occurrence.
     *
     * @param object the object to search for.
     * @return the index of the first occurrence of the object or -1 if the
     * object was not found.
     */
    int indexOf(T object);

    /**
     * Adds the objects in the specified collection to the end of this {@code Adapter}. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param collection the collection of objects.
     * @throws NullPointerException if {@code collection} is {@code null}.
     */
    void addAll(Collection<? extends T> collection);

    /**
     * Adds the objects in the specified varargs to the end of this {@code Adapter}. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param items the varargs of objects.
     */
    void addAll(T... items);

    /**
     * Adds the specified object at the end of this {@code Adapter}.
     *
     * @param object the object to add.
     */
    void add(T object);

    /**
     * Inserts the specified object into this {@code Adapter} at the specified
     * location. The object is inserted before any previous element at the
     * specified location. If the location is equal to the size of this
     * {@code Adapter}, the object is added at the end.
     *
     * @param index  the index at which to insert the object.
     * @param object the object to add.
     * @throws IndexOutOfBoundsException when {@code location < 0 || location > size()}
     */
    void add(int index, T object);

    /**
     * Removes the object at the specified location from this {@code Adapter}.
     *
     * @param index the index of the object to remove.
     * @throws IndexOutOfBoundsException when {@code location < 0 || location >= size()}
     */
    void removeAt(int index);

    /**
     * Removes the first occurrence of the specified object from this {@code Adapter}.
     *
     * @param object the object to remove.
     */
    void remove(T object);


    void move(int from, int to);

    /**
     * Removes all elements from this {@code Adapter}, leaving it empty.
     *
     * @see #isEmpty
     * @see #size
     */
    void clear();


    BindingViewHolder<T> generateViewHolder(ViewDataBinding viewDataBinding, int viewType);

    /**
     * @param collection the collection of objects.
     */
    void resetAll(Collection<? extends T> collection);

    /**
     * Returns whether this {@code Adapter} contains no elements.
     *
     * @return {@code true} if this {@code Adapter} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this {@code Adapter}.
     *
     * @return the number of elements in this {@code Adapter}.
     */
    int size();


    List<T> getObjects();

    int getLayoutResId(int viewType);


    T get(int location);


    BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType);


    ViewDataBinding generateView(ViewGroup parent, int viewType);


    Class<? extends BindingViewHolder<? extends T>> getViewHolderClass(int viewType);


    interface OnItemClickListener<T> {

        void onItemClick(BindingAdapter<T> adapter, View view, T object, int position);
    }

    interface OnItemLongClickListener<T> {

        void onLongItemClick(BindingAdapter<T> adapter, View view, T object, int position);
    }
}
