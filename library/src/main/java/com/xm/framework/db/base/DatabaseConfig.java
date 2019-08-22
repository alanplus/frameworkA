package com.xm.framework.db.base;

import android.content.Context;


import java.util.List;

/**
 * Created by Mouse on 2019-08-21.
 */
public interface DatabaseConfig {


    /**
     * 获取数据库名
     *
     * @return
     * @author dushengjun
     */
    String getDatabaseName();

    /**
     * 获取数据库版本号
     *
     * @return
     * @author dushengjun
     */
    int getDatabaseVersion();

    /**
     * 获取数据库库中所有表的类
     *
     * @return
     * @author dushengjun
     */
    List<Class<? extends SQLiteManager.SQLiteTable>> getTables(Context context);
}
