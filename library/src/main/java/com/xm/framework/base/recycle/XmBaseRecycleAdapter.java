package com.xm.framework.base.recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mouse on 2018/10/19.
 */
public abstract class XmBaseRecycleAdapter<T> extends RecyclerView.Adapter<XmBaseRecycleAdapter.CommonViewHolder> {

    protected List<T> list;
    protected Context context;

    protected static final int TYPE_HEAD = 100;
    protected static final int TYPE_FOOT = 101;
    protected static final int TYPE_COMMON = 102;

    protected OnItemClickListener<T> onItemClickListener;

    // 0 隐藏所有 1 加载更多 2 已完成 3 加载失败
    private @FootView.FooterState
    int footState;

    private CommonViewHolder footerHolder;

    public XmBaseRecycleAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            return footerHolder = new CommonViewHolder(new FootView(context));
        }
        return onCreateViewHolder(parent);
    }

    public abstract CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    public abstract void onBindCommonViewHolder(@NonNull CommonViewHolder commonViewHolder, int position);

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder commonViewHolder, int position) {
        View itemView = commonViewHolder.itemView;
        if (itemView instanceof FootView) {
            ((FootView) itemView).setData(footState);
        } else {
            onBindCommonViewHolder(commonViewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return null == list ? 1 : list.size() + 1;
    }

    public static class CommonViewHolder extends RecyclerView.ViewHolder {

        public CommonViewHolder(View itemView) {
            super(itemView);
        }
    }

    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClickListener(int position, T t, View view, boolean isLongClick);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOT;
        }
        return TYPE_COMMON;
    }

    public void setFootState(@FootView.FooterState int footState) {
        this.footState = footState;
        if (null != footerHolder) {
            FootView footView = (FootView) footerHolder.itemView;
            footView.setData(this.footState);
        }
    }
}
