package com.xm.framework.base.layout.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.xm.framework.base.layout.ViewAnimationConfig;

/**
 * Created by Mouse on 2019/4/19.
 */
public class AnimationBuilder {

    public static void startTranslateAnimation(View view, ViewAnimationConfig viewAnimationConfig) {
        view.setVisibility(View.VISIBLE);
        int startX = viewAnimationConfig.getStartX(view);
        int startY = viewAnimationConfig.getStartY(view);
        int endX = viewAnimationConfig.getEndX(view);
        int endY = viewAnimationConfig.getEndY(view);
        int duration = viewAnimationConfig.getDuration();
        int repeatMode = viewAnimationConfig.getRepeatMode();
        int count = viewAnimationConfig.getCount();
        Animation.AnimationListener animationListener = viewAnimationConfig.getAnimationListener(view);
        TranslateAnimation translateAnimation = new TranslateAnimation(startX, endX, startY, endY);
        translateAnimation.setDuration(duration);
        translateAnimation.setRepeatMode(repeatMode);
        translateAnimation.setRepeatCount(count);
        if (null != animationListener) {
            translateAnimation.setAnimationListener(animationListener);
        }
        view.startAnimation(translateAnimation);
    }
}
