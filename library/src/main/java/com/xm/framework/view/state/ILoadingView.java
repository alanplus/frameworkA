package com.xm.framework.view.state;

import android.view.View;

/**
 * Created by Mouse on 2018/10/22.
 */
public interface ILoadingView {

    void setText(String text);
    void start();
    View getView();
}
