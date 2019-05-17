package com.xm.framework.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Mouse on 2019-05-17.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> list;

    public BasicAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return context;
    }

    public List<T> getList() {
        return list;
    }
}
