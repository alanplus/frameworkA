package com.xm.framework.base;

import android.view.View;
import android.view.ViewGroup;

import com.xm.framework.base.dialog.LoadingDialog;
import com.xm.framework.tools.ToastManager;
import com.xm.framework.view.state.IStateViewListener;

/**
 * Created by Mouse on 2019-06-18.
 */
public abstract class StateBaseFragment<T> extends BaseFragment implements IBaseView<T> {

    private LoadingDialog loadingDialog;

    @Override
    protected String getPageName() {
        return null;
    }

    @Override
    public void showSuccess(T t) {

    }

    @Override
    public void showFailure(int code, String msg, boolean retry) {
        IStateViewListener stateView = findStateView((ViewGroup) mRoot.findViewById(android.R.id.content));
        if (null != stateView) {
            stateView.showFailureState(msg, retry);
        }
    }

    @Override
    public void showLoading(String msg) {
        IStateViewListener stateView = findStateView((ViewGroup) mRoot);
        if (null != stateView) {
            stateView.showLoadingState(msg);
        }
    }

    @Override
    public void showLoadingDialog(String text) {
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setText(text);
        loadingDialog.show();
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog("正在加载...");
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
    public void dismissLoadingDialog() {
        loadingDialog.dismiss();
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
    public void showToast(String msg) {
        ToastManager.getInstance().showToast(getContext(), msg);
    }

    @Override
    public void finishPage() {
        getActivity().finish();
    }

    @Override
    public LoadingDialog getLoadingDialog() {
        return loadingDialog;
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
