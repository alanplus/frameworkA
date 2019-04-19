package com.xm.framework.base.layout;

/**
 * Created by Mouse on 2019/4/19.
 */
public interface IBaseLayout {

    void show();

    void hidden();

    void setShowAnimationConfig(ViewAnimationConfig viewShowAnimationConfig);
    void setHiddenAnimationConfig(ViewAnimationConfig viewShowAnimationConfig);
}
