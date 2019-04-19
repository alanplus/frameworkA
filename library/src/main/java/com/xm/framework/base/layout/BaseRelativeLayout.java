package com.xm.framework.base.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by Mouse on 2019/4/12.
 */
public abstract class BaseRelativeLayout extends RelativeLayout implements IBaseLayout{

    private BaseLayoutHelper baseLayoutHelper;

    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        baseLayoutHelper = new BaseLayoutHelper(this);
        LayoutInflater.from(getContext()).inflate(getContentId(), this);

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
