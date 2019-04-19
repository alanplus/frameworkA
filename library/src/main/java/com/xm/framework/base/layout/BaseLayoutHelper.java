package com.xm.framework.base.layout;

import android.view.View;

import com.xm.framework.base.layout.animation.AnimationBuilder;
import com.xm.framework.base.layout.animation.DefaultShowAnimationInflater;

/**
 * Created by Mouse on 2019/4/19.
 */
public class BaseLayoutHelper implements IBaseLayout {

    private View view;
    private ViewAnimationConfig viewShowAnimationConfig;
    private ViewAnimationConfig viewHiddenAnimationConfig;

    public BaseLayoutHelper(View view) {
        this.view = view;
    }


    @Override
    public void show() {
        AnimationBuilder.startTranslateAnimation(view, viewShowAnimationConfig == null ? new DefaultShowAnimationInflater() : viewShowAnimationConfig);
    }

    @Override
    public void hidden() {
        AnimationBuilder.startTranslateAnimation(view, viewHiddenAnimationConfig == null ? new DefaultShowAnimationInflater() : viewHiddenAnimationConfig);
    }

    @Override
    public void setShowAnimationConfig(ViewAnimationConfig viewShowAnimationConfig) {
        this.viewShowAnimationConfig = viewShowAnimationConfig;
    }

    @Override
    public void setHiddenAnimationConfig(ViewAnimationConfig viewShowAnimationConfig) {
        this.viewHiddenAnimationConfig = viewShowAnimationConfig;
    }


}