package com.xm.framework.base.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Mouse on 2019/4/12.
 */
public abstract class BaseLinearLayout extends LinearLayout {

    public BaseLinearLayout(Context context) {
        this(context, null);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(getContentId(), this);

    }

    protected abstract int getContentId();
}
