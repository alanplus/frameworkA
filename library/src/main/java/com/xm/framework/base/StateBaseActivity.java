package com.xm.framework.base;

import android.view.View;
import android.view.ViewGroup;

import com.xm.framework.base.dialog.LoadingDialog;
import com.xm.framework.tools.ToastManager;
import com.xm.framework.view.state.IStateViewListener;

/**
 * Created by Mouse on 2019/4/9.
 */
public abstract class StateBaseActivity<T> extends BaseActivity implements IBaseView<T> {

    private LoadingDialog loadingDialog;

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

    @Override
    public void showLoadingDialog(String text) {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setText(text);
        loadingDialog.show();
    }

    @Override
    public void dismissSuccessLoadingDialog(String text) {
        if (null != loadingDialog) {
            loadingDialog.dismiss(text, true);
        }
    }

    @Override
    public void dismissFailedLoadingDialog(String text) {
        if (null != loadingDialog) {
            loadingDialog.dismiss(text, false);
        }
    }

    @Override
    public void dismissSuccessLoadingDialog(String text, LoadingDialog.OnDialogDismissListener onDialogDismissListener) {
        if (null != loadingDialog) {
            loadingDialog.dismiss(text, true, onDialogDismissListener);
        }
    }

    @Override
    public void dismissFailedLoadingDialog(String text, LoadingDialog.OnDialogDismissListener onDialogDismissListener) {
        if (null != loadingDialog) {
            loadingDialog.dismiss(text, false, onDialogDismissListener);
        }
    }

    @Override
    public void dismissSuccessLoadingDialog() {
        dismissSuccessLoadingDialog("加载成功");
    }

    @Override
    public void dismissFailedLoadingDialog() {
        dismissFailedLoadingDialog("加载失败");
    }

    @Override
    public void showToast(String msg) {
        ToastManager.getInstance().showToast(this, msg);
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog("正在加载...");
    }
}
