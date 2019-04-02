package com.xm.framework.database;

import android.content.Context;

import com.xm.framework.database.config.ConfigDAOImpl;
import com.xm.framework.database.config.IConfigDAO;


/**
 * Dao工厂
 * Title: DaoFactory
 *
 * @date 2018/8/11 10:22
 * @Company WeiCi
 */
public class DaoFactory {

    public static synchronized IConfigDAO getConfigDAO(Context context) {
        return new ConfigDAOImpl(context);
    }

}
