package com.xm.framework.global;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;

import com.xm.framework.R;
import com.xm.framework.database.DatabaseConfig;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Mouse on 2018/10/18.
 */
public abstract class AndroidToolsConfig {

    public AndroidToolsConfig(Context context) {
        this.context = context;
    }

    public static AndroidToolsConfig androidToolsConfig;

    public Context context;

    public DatabaseConfig getDatabaseConfig() {
        return null;
    }

    public Handler getHandler() {
        return null;
    }

    public static void init(AndroidToolsConfig androidToolsConfig) {
        AndroidToolsConfig.androidToolsConfig = androidToolsConfig;
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
