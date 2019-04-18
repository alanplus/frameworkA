package com.xm.framework.base.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by Mouse on 2019/4/12.
 */
public abstract class BaseRelativeLayout extends RelativeLayout {

    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(getContentId(), this);

    }

    protected abstract int getContentId();
}
