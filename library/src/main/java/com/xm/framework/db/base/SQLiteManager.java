package com.xm.framework.db.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.xm.framework.global.AndroidToolsConfig;
import com.xm.framework.tools.Logger;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * sqlite数据库管理类，负责表的初始化和表的更
 *
 * @author Administrator
 */
public class SQLiteManager extends SQLiteOpenHelper {
    protected static volatile SQLiteManager instance;
    protected Context mContext;

    protected static DatabaseConfig mConfig;
    protected static volatile SQLiteDatabase db;

    protected SQLiteManager(Context context, String name, int version) {
        super(context, name, null, version);
        mContext = context;
    }

    public static synchronized SQLiteDatabase getDB(Context context,
                                             DatabaseConfig config) {
        if (db == null) {
            mConfig = config;
            if (instance == null) {
                instance = new SQLiteManager(context, config.getDatabaseName(),
                        config.getDatabaseVersion());
            }
            db = instance.getWritableDatabase();
            String externalDatabaseName = AndroidToolsConfig.androidToolsConfig.getExternalDatabaseName();
            if (!TextUtils.isEmpty(externalDatabaseName)) {
                db.execSQL(String.format("attach DATABASE '/data/data/%s/databases/%s' AS 'c'", context.getPackageName(), externalDatabaseName));
            }

        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        List<Class<? extends SQLiteTable>> classes = mConfig.getTables(mContext);
        for (Class<? extends SQLiteTable> clazz : classes) {
            try {
                Constructor<? extends SQLiteTable> con = clazz
                        .getConstructor();
                SQLiteTable table = con.newInstance();
                table.onCreate(db);
            } catch (Exception e) {
                Logger.d("database create error:" + e.getMessage());
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<Class<? extends SQLiteTable>> classes = mConfig.getTables(mContext);
        for (Class<? extends SQLiteTable> clazz : classes) {
            try {
                Constructor<? extends SQLiteTable> con = clazz
                        .getConstructor();
                SQLiteTable table = con.newInstance();
                table.onUpdate(db, oldVersion, newVersion);
            } catch (Exception e) {
                Logger.d("database update error:" + e.getMessage());
            }
        }
    }

    /**
     * DAO表接口
     *
     * @author DuShengjun<dushengjun                                                               @                                                               gmail.com>
     */
    public interface SQLiteTable {
        String COL_TYPE_AUTO_ID = "INTEGER PRIMARY KEY";
        String COL_TYPE_FLOAT = "FLOAT";
        String COL_TYPE_TEXT = "TEXT";
        String COL_TYPE_INT = "INT";
        String COL_TYPE_LONG = "LONG";

        String COL_TYPE_PRIMARY_KEY = "PRIMARY KEY";
        String COL_TYPE_NOT_NULL = " NOT NULL ";
        String COL_TYPE_DEFAULT = " DEFAULT(%s) ";

        void onCreate(SQLiteDatabase database);
        void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion);
    }

    public static void destroy() {
        if (null != db) {
            db.close();
            db = null;
            instance = null;
        }
    }
}
