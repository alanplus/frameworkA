package com.xm.framework.base.layout.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.xm.framework.base.layout.ViewAnimationConfig;
import com.xm.framework.tools.ViewTools;

/**
 * Created by Mouse on 2019/4/19.
 */
public class DefaultShowAnimationInflater implements ViewAnimationConfig {


    @Override
    public int getStartX(View view) {
        return 0;
    }

    @Override
    public int getEndX(View view) {
        return 0;
    }

    @Override
    public int getStartY(View view) {
        return ViewTools.getTargetHeight(view);
    }

    @Override
    public int getEndY(View view) {
        return 0;
    }

    @Override
    public int getDuration() {
        return 500;
    }

    @Override
    public int getRepeatMode() {
        return Animation.RESTART;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Interpolator getInterpolator() {
        return new LinearInterpolator();
    }

    @Override
    public Animation.AnimationListener getAnimationListener(View view) {
        return null;
    }
}
