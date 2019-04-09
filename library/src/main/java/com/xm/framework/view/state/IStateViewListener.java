package com.xm.framework.view.state;

import android.widget.TextView;

/**
 * Created by Mouse on 2018/9/20.
 */

public interface IStateViewListener {

    void showLoadingState(String text);
    void showFailureState(String text, boolean isRetry);
    void showSuccessState();
    TextView getRetryView();
}
