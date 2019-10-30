package com.xm.framework.global;

import android.content.Context;
import android.os.Handler;

import com.xm.framework.db.base.DatabaseConfig;


/**
 * @author Alan
 * 时 间：2019-10-30
 * 简 述：<功能简述>
 */
public class LibConfig {

    private static AndroidToolsConfig androidToolsConfig;

    public static void init(AndroidToolsConfig androidToolsConfig) {
        LibConfig.androidToolsConfig = androidToolsConfig;
    }

    /**
     * 获取应用的上下文
     *
     * @return
     */
    public static Context getApplicationContext() {
        if (androidToolsConfig != null) {
            return androidToolsConfig.context;
        }
        return null;
    }

    /**
     * 获取activity管理器
     *
     * @return
     */
    public static IActivityListener getIActivityListener() {
        if (androidToolsConfig != null) {
            return androidToolsConfig.getIActivityListener();
        }
        return null;
    }

    /**
     * 获取全局handler
     *
     * @return
     */
    public static Handler getGloableHandler() {
        if (androidToolsConfig != null) {
            return androidToolsConfig.getHandler();
        }
        return null;
    }

    /**
     * 获取全局数据库配置
     *
     * @return
     */
    public static DatabaseConfig getDatabaseConfig() {
        if (androidToolsConfig != null) {
            return androidToolsConfig.getDatabaseConfig();
        }
        return null;
    }

    /**
     * 获取外部数据名称
     * @return
     */
    public static String getExternalDatabaseName(){
        if (androidToolsConfig != null) {
            return androidToolsConfig.getExternalDatabaseName();
        }
        return null;
    }
}
