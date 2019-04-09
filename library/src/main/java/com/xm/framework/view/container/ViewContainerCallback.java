package com.xm.framework.view.container;

import android.view.View;

/**
 * Created by Mouse on 2019/3/18.
 */
public interface ViewContainerCallback<T> {

    View getView(T t, int position, ViewContainer viewContainer);
}
