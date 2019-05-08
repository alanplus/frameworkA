package com.xm.framework.base;

import com.xm.framework.base.dialog.LoadingDialog;

/**
 * Created by Mouse on 2019/4/1.
 */
public interface IBaseView<T> {

    void showSuccess(T t);

    void showFailure(int code, String msg, boolean retry);

    void showLoading(String msg);

    void showLoadingDialog(String text);

    void showLoadingDialog();

    void dismissSuccessLoadingDialog();

    void dismissFailedLoadingDialog();

    void dismissSuccessLoadingDialog(String text);

    void dismissFailedLoadingDialog(String text);

    void dismissSuccessLoadingDialog(String text, LoadingDialog.OnDialogDismissListener onDialogDismissListener);

    void dismissFailedLoadingDialog(String text, LoadingDialog.OnDialogDismissListener onDialogDismissListener);

    void showToast(String msg);
}
