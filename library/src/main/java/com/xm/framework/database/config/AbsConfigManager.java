package com.xm.framework.database.config;

import android.content.Context;

import com.xm.framework.database.DaoFactory;

/**
 * Created by Mouse on 2019/2/26.
 */
public abstract class AbsConfigManager {

    protected IConfigDAO sp;
    protected Context mContext;

    protected AbsConfigManager(Context context) {
        mContext = context.getApplicationContext();
        sp = DaoFactory.getConfigDAO(mContext);
    }

}
