package com.xm.framework.base.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Mouse on 2019/4/12.
 */
public abstract class BaseLinearLayout extends LinearLayout implements IBaseLayout {

    private BaseLayoutHelper baseLayoutHelper;

    public BaseLinearLayout(Context context) {
        this(context, null);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(getContentId(), this);
        baseLayoutHelper = new BaseLayoutHelper(this);
    }

    protected abstract int getContentId();

    @Override
    public void show() {
        baseLayoutHelper.show();
    }

    @Override
    public void hidden() {
        baseLayoutHelper.hidden();
    }

    @Override
    public void setShowAnimationConfig(ViewAnimationConfig viewShowAnimationConfig) {
        baseLayoutHelper.setShowAnimationConfig(viewShowAnimationConfig);
    }

    @Override
    public void setHiddenAnimationConfig(ViewAnimationConfig viewShowAnimationConfig) {
        baseLayoutHelper.setHiddenAnimationConfig(viewShowAnimationConfig);
    }
}
