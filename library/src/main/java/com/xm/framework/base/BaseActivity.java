package com.xm.framework.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mouse on 2019/4/1.
 */
public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseView<T> {

    private static List<Activity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
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
}
