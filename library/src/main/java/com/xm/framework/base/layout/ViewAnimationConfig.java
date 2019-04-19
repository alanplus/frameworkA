package com.xm.framework.base.layout;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

/**
 * Created by Mouse on 2019/4/19.
 */
public interface ViewAnimationConfig {

    int getStartX(View view);

    int getEndX(View view);

    int getStartY(View view);

    int getEndY(View view);

    int getDuration();

    int getRepeatMode();

    int getCount();

    Interpolator getInterpolator();

    Animation.AnimationListener getAnimationListener(View view);

}
