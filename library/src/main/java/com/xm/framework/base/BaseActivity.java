package com.xm.framework.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xm.framework.R;
import com.xm.framework.global.LibConfig;
import com.xm.framework.tools.AndroidTools;
import com.xm.framework.tools.statusbar.StatusBarTools;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mouse on 2019/4/1.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static List<Activity> activityList = new ArrayList<>();

    public static final int MODE_COMMON = 0;
    public static final int MODE_IMMERSION = 1;
    public static final int MODE_TRANSLATE = 2;

    @IntDef({MODE_COMMON, MODE_IMMERSION, MODE_TRANSLATE})
    @interface Mode {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onCreate(this);
        }
        activityList.add(this);
        initEventBus();
        initStatusBar();
        initView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onStart(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onPause(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onStop(this);
        }

    }

    private void initEventBus() {
        if (isOpenEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    protected boolean isOpenEventBus() {
        return false;
    }

    protected void unregistEvenBus() {
        if (isOpenEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract void initView();

    protected void initStatusBar() {
        int mode = getMode();
        switch (mode) {
            case MODE_IMMERSION:
                setImmersionMode();
                break;
            case MODE_TRANSLATE:
                setTranslateMode();
                break;
            case MODE_COMMON:
            default:
                break;
        }
    }

    protected abstract int getContentId();

    public static void finishAll() {
        for (Activity a : activityList) {
            a.finish();
            a.overridePendingTransition(0, 0);
        }
    }

    public static void finishItem(String name) {
        for (Activity a : activityList) {
            if (a.getClass().getName().equals(name)) {
                a.finish();
            }
        }
    }

    protected @Mode
    int getMode() {
        return AndroidTools.getColorFromTheme(this, R.attr.activity_mode, MODE_IMMERSION);
    }

    protected void setImmersionMode() {
        StatusBarTools.getStatusBarTools().setStatusBarColor(this, getStatusBarColor(), isWhite());
    }

    protected void setTranslateMode() {
        StatusBarTools.setTranslucent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != LibConfig.getIActivityListener()) {
            LibConfig.getIActivityListener().onDestroy(this);
        }

        unregistEvenBus();
        activityList.remove(this);
    }

    public Activity getActivity() {
        return this;
    }

    protected @ColorInt
    int getStatusBarColor() {
        return AndroidTools.getColorFromTheme(this, R.attr.status_bar_color, Color.WHITE);
    }

    protected boolean isWhite() {
        return AndroidTools.getBoolFromTheme(this, R.attr.status_bar_text_is_white, false);
    }
}
