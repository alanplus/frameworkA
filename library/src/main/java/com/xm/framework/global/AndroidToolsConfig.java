package com.xm.framework.global;

import android.content.Context;
import android.os.Handler;

import com.xm.framework.R;
import com.xm.framework.db.base.DatabaseConfig;
import com.xm.framework.tools.AndroidTools;

import java.io.File;

/**
 * Created by Mouse on 2018/10/18.
 */
public abstract class AndroidToolsConfig {


    public Context context;

    public AndroidToolsConfig(Context context) {
        this.context = context;
    }

    /**
     * 数据库配置
     *
     * @return
     */
    public DatabaseConfig getDatabaseConfig() {
        return null;
    }

    /**
     * 获取Activity管理器
     *
     * @return
     */
    public IActivityListener getIActivityListener() {
        return null;
    }

    public Handler getHandler() {
        return null;
    }


    public String getDownloadTempPath() {
        String path = "/data/data/" + context.getPackageName() + "/temp/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public String getExternalDatabaseName() {
        return null;
    }

}
