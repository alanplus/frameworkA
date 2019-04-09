package com.xm.framework.view.container;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.xm.framework.R;
import com.xm.framework.tools.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mouse on 2019/3/18.
 */
public class ViewContainer<T> implements Animation.AnimationListener {

    private FrameLayout mContainer;
    private ViewContainerCallback viewContainerCallback;
    private HashMap<String, View> map = new HashMap<>();
    private Context context;
    private View currentCard;
    private List<T> list;
    private int currentPosition;
    private boolean isAniming;

    public ViewContainer(Context context, FrameLayout frameLayout, ViewContainerCallback viewContainerCallback, List<T> list) {
        this.mContainer = frameLayout;
        this.viewContainerCallback = viewContainerCallback;
        this.context = context;
        this.list = list;
        currentPosition = 0;
        showNextCard(currentPosition, false);
        isAniming = false;
    }

    public void showNextCard() {
        if (currentPosition < list.size() - 1) {
            showNextCard(++currentPosition, true);
        }
    }

    public void showNextCard(int position, boolean isAnim) {
        if (null == context || viewContainerCallback == null) return;
        T t = list.get(position);
        currentCard = viewContainerCallback.getView(t, position, this);
        mContainer.addView(currentCard);

        if (isAnim) {
            View preView = getPreChildView();
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_right_to_left);
            animation.setAnimationListener(this);
            currentCard.startAnimation(animation);
            if (null != preView) {
                preView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_to_left_out));
            }
        }
        int childCount = mContainer.getChildCount();
        if (childCount > 2) {
            View child = mContainer.getChildAt(0);
            mContainer.removeViewAt(0);
            addMapCache(child);
        }
    }


    private View getPreChildView() {
        int childCount = mContainer.getChildCount();
        if (childCount >= 2) {
            return mContainer.getChildAt(childCount - 2);
        }
        return null;
    }


    public void addMapCache(View view) {
        Logger.d(view.getClass().getName());
        map.put(view.getClass().getName(), view);
    }

    public void clear() {
        map.clear();
    }


    public void destroy() {
        map.clear();
        mContainer = null;
        context = null;
    }

    public View getCurrentCard() {
        return currentCard;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public ViewContainerCallback getViewContainerCallback() {
        return viewContainerCallback;
    }

    public FrameLayout getmContainer() {
        return mContainer;
    }

    public List<T> getList() {
        return list;
    }

    public View hasView(String string) {
        return map.get(string);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isAniming = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isAniming = false;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public boolean isAniming() {
        return isAniming;
    }
}
