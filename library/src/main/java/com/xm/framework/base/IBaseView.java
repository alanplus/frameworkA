package com.xm.framework.base;

/**
 * Created by Mouse on 2019/4/1.
 */
public interface IBaseView<T> {

    void showSuccess(T t);

    void showFailure(int code, String msg);
}
