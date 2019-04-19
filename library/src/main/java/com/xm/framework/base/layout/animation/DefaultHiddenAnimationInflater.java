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
public class DefaultHiddenAnimationInflater implements ViewAnimationConfig {


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
        return 0;
    }

    @Override
    public int getEndY(View view) {
        return ViewTools.getTargetHeight(view);
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
    public Animation.AnimationListener getAnimationListener(final View view) {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(null!=view){
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
