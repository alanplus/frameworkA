package com.xm.framework.base;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Mouse on 2019/4/1.
 */
public class BasePresenter<T> {

    protected Context context;

    protected IBaseView<T> iBaseView;

    public BasePresenter(Context context, IBaseView<T> iBaseView) {
        this.context = context;
        this.iBaseView = iBaseView;
    }

    public void init(Intent intent){

    }

}
