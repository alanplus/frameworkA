package com.xm.framework.db.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xm.framework.global.AndroidToolsConfig;
import com.xm.framework.global.LibConfig;
import com.xm.framework.tools.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author Alan
 */
public abstract class BaseDAO<T> implements SQLiteManager.SQLiteTable, IBaseDAO<T> {
    public static final String[] ALL_COLS = new String[]{"*"};
    protected Context mContext;
    protected String mTableName;
    protected DatabaseConfig mConfig;
    protected List<Class<? extends IPatcher<T>>> mPatcherList;


    /**
     * @param tableName
     */
    public BaseDAO(String tableName) {
        mContext = LibConfig.getApplicationContext();
        mConfig = LibConfig.getDatabaseConfig();
        mTableName = tableName;
    }

    /*

      ------------------------------------抽象方法--------------------------------------

     */

    /**
     * 获取sqlite数据
     *
     * @return
     */
    protected SQLiteDatabase getDatabase() {
        return SQLiteManager.getDB(mContext, mConfig);
    }


    /**
     * 创建表
     *
     * @param database
     */
    @Override
    public abstract void onCreate(SQLiteDatabase database);

    /**
     * 从游标中取一个对象
     *
     * @param cursor
     * @return
     */
    protected abstract T findByCursor(Cursor cursor);

    /**
     * 获取对象的ContentValues
     *
     * @return
     */
    protected abstract ContentValues getContentValues(T t);


    /*

      ------------------------------------常用方法--------------------------------------

     */

    /**
     * 通过sql 获取 List
     *
     * @param sql
     * @return
     */
    public List<T> findListBySql(String sql) {
        Cursor cursor = getDatabase().rawQuery(sql, null);
        return findListByCursor(cursor, -1);
    }

    @Override
    public List<T> findAll() {
        return findListBySql("select * from " + mTableName);
    }

    /**
     * 通过sql获取对象
     *
     * @param sql
     * @return
     */
    public T findBySql(String sql) {
        Logger.e("test_sql", sql);
        Cursor cursor = getDatabase().rawQuery(sql, null);
        List<T> list = findListByCursor(cursor, 1);
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 获取列表数量
     *
     * @param sql
     * @return
     */
    public int getCursorCount(String sql) {
        Cursor cursor = getDatabase().rawQuery(sql, null);
        try {
            return null == cursor ? 0 : cursor.getCount();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 删除全部
     */
    @Override
    public void deleteAll() {
        getDatabase().delete(mTableName, null, null);
    }

    /**
     * 保存对象
     *
     * @return
     */
    @Override
    public boolean save(T t) {
        return getDatabase().insert(mTableName, null, getContentValues(t)) > 0;
    }

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    @Override
    public boolean save(List<T> list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        getDatabase().beginTransaction();
        for (T t : list) {
            save(t);
        }
        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
        return true;
    }

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    @Override
    public boolean replace(T t) {
        return getDatabase().replace(mTableName, null, getContentValues(t)) > 0;
    }

    /**
     * 批量更新对象
     *
     * @param list
     * @return
     */
    @Override
    public boolean replace(List<T> list) {
        if (null == list || list.size() == 0) {
            return true;
        }

        getDatabase().beginTransaction();
        for (T t : list) {
            replace(t);
        }
        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
        return true;
    }

    /**
     * 删除表
     *
     * @param database
     */
    public void dropTable(SQLiteDatabase database) {
        String sql = "DROP TABLE IF EXISTS " + mTableName;
        database.execSQL(sql);
    }

    /**
     * 添加列
     *
     * @param database
     * @param columns
     */
    public void addColumns(SQLiteDatabase database, Map<String, String> columns) {
        if (columns == null) {
            return;
        }
        Object[] keys = columns.keySet().toArray();
        String sql = null;
        for (Object key : keys) {
            sql = "ALTER TABLE " + mTableName + " ADD `" + key.toString()
                    + "` " + columns.get(key);
            database.execSQL(sql);
        }
    }

    /**
     * 执行sql语句
     *
     * @param database
     * @param sqls
     */
    public void execSQLs(SQLiteDatabase database, List<String> sqls) {
        if (sqls == null) {
            return;
        }
        for (String sql : sqls) {
            database.execSQL(sql);
        }
    }

    /*

      ------------------------------------工具方法--------------------------------------

     */

    protected int getIntFromCusor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) return 0;
        return cursor.getInt(columnIndex);
    }

    protected String getStringFromCusor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) return "";
        return cursor.getString(columnIndex);
    }

    protected Long getLongFromCusor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) return 0L;
        return cursor.getLong(columnIndex);
    }

    protected Float getFloatFromCusor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex == -1) return 0F;
        return cursor.getFloat(columnIndex);
    }

    protected final String[] args(Object... args) {
        int length = args.length;
        String[] arg = new String[args.length];
        for (int i = 0; i < length; i++) {
            arg[i] = String.valueOf(args[i]);
        }
        return arg;
    }

    /*

      ------------------------------------系统工具方法--------------------------------------

     */

    @Override
    public final void onUpdate(SQLiteDatabase database, int oldVersion,
                               int newVersion) {
        if (this.mPatcherList != null) {
            for (Class<? extends IPatcher<T>> patcherClazz : mPatcherList) {
                IPatcher<T> inst;
                try {
                    inst = patcherClazz.newInstance();
                    int max = inst.getSupportMaxVersion();
                    if (oldVersion <= max) {
                        inst.execute(this, database, mContext);
                    }
                } catch (Exception e) {
                    Logger.e("weici_error", Log.getStackTraceString(e));
                }
            }
        }
    }


    /**
     * 根据游标获取指定个数的列
     *
     * @param cursor
     * @return
     */
    private List<T> findListByCursor(Cursor cursor, int max) {
        List<T> ret = new ArrayList<>();
        if (null == cursor) {
            return ret;
        }
        max = max < 0 ? cursor.getCount() : max;
        try {
            while (cursor.moveToNext() && ret.size() < max) {
                T t = findByCursor(cursor);
                ret.add(t);
            }
        } finally {
            cursor.close();
        }
        return ret;
    }

    /**
     * 创建表
     *
     * @param database
     * @param columns
     */
    protected void createTable(SQLiteDatabase database, HashMap<String, String> columns, List<String> keyList) {
        String sqlFormat = "CREATE TABLE %s (%s)";
        StringBuffer sql = new StringBuffer();
        Object[] keys = columns.keySet().toArray();
        for (Object key : keys) {
            sql.append(key + " " + columns.get(key));
            if (key != keys[keys.length - 1]) {
                sql.append(",");
            }
        }
        if (keyList != null && keyList.size() > 0) {
            sql.append(",PRIMARY KEY (");
            for (int i = 0; i < keyList.size(); i++) {
                sql.append(keyList.get(i));
                sql.append(i == keyList.size() - 1 ? ")" : ",");
            }
        }
        String sqlStr = String.format(sqlFormat, mTableName, sql.toString());
        Logger.e("weici_db", sqlStr);
        database.execSQL(sqlStr);
    }

    /**
     * 注册补丁类,此类必须拥有用无参数的构造函数
     *
     * @param patcherClazz
     */
    public BaseDAO<T> registerPatcher(Class<? extends IPatcher<T>> patcherClazz) {
        if (mPatcherList == null) {
            mPatcherList = new ArrayList<>();
        }
        mPatcherList.add(patcherClazz);
        return this;
    }

}
