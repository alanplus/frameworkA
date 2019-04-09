package com.xm.framework.base;

import android.view.View;
import android.view.ViewGroup;

import com.xm.framework.view.state.IStateViewListener;

/**
 * Created by Mouse on 2019/4/9.
 */
public abstract class StateBaseActivity<T> extends BaseActivity implements IBaseView<T> {

    @Override
    public void showFailure(int code, String msg, boolean isRetry) {
        IStateViewListener stateView = findStateView((ViewGroup) findViewById(android.R.id.content));
        if (null != stateView) {
            stateView.showFailureState(msg, isRetry);
        }
    }

    @Override
    public void showLoading(String msg) {
        IStateViewListener stateView = findStateView((ViewGroup) findViewById(android.R.id.content));
        if (null != stateView) {
            stateView.showLoadingState(msg);
        }
    }

    @Override
    public void showSuccess(T t) {
        IStateViewListener stateView = findStateView((ViewGroup) findViewById(android.R.id.content));
        if (stateView != null) {
            stateView.showSuccessState();
        }
    }

    protected IStateViewListener findStateView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount == 0) {
            return null;
        } else {
            for (int i = 0; i < childCount; ++i) {
                View v = viewGroup.getChildAt(i);
                if (v instanceof IStateViewListener) {
                    return (IStateViewListener) v;
                }
                if (v instanceof ViewGroup) {
                    IStateViewListener stateView = this.findStateView((ViewGroup) v);
                    if (null != stateView) {
                        return stateView;
                    }
                }
            }
            return null;
        }
    }
}
