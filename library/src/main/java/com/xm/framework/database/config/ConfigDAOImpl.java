package com.xm.framework.database.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.xm.framework.database.BaseDAO;
import com.xm.framework.global.AndroidToolsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigDAOImpl extends BaseDAO<Config> implements IConfigDAO {

    public static final String TABLE_NAME = "config";

    public ConfigDAOImpl(Context context) {
        super(TABLE_NAME, context, AndroidToolsConfig.androidToolsConfig.getDatabaseConfig());
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Map<String, String> columns = new HashMap<>();
        columns.put(Config.TABLE_COLUM_ID, COL_TYPE_AUTO_ID);
        columns.put(Config.TABLE_COLUM_KEY, COL_TYPE_TEXT);
        columns.put(Config.TABLE_COLUM_VALUE, COL_TYPE_TEXT);
        createTable(database, columns);
    }

    @Override
    public void setValue(String key, String val) {
        if (exist(key)) {
            update(key, val);
        } else {
            save(key, val);
        }
    }

    @Override
    public void setValue(String key, int val) {
        if (exist(key)) {
            update(key, String.valueOf(val));
        } else {
            save(key, String.valueOf(val));
        }
    }

    @Override
    public void setValue(String key, boolean val) {
        if (exist(key)) {
            update(key, String.valueOf(val));
        } else {
            save(key, String.valueOf(val));
        }
    }

    @Override
    public void setValue(String key, long val) {
        if (exist(key)) {
            update(key, String.valueOf(val));
        } else {
            save(key, String.valueOf(val));
        }

    }

    @Override
    protected Config findByCursor(Cursor cursor, int i) {
        Config config = new Config();
        int idx = cursor.getColumnIndex(Config.TABLE_COLUM_KEY);
        config.setKey(cursor.getString(idx));
        idx = cursor.getColumnIndex(Config.TABLE_COLUM_VALUE);
        config.setValue(cursor.getString(idx));
        return config;
    }

    @Override
    public String getStringByKey(String key, String defValue) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        String val = defValue;
        if (null != config && !TextUtils.isEmpty(config.getValue())) {
            val = config.getValue();
        }
        return val;
    }

    @Override
    public int getIntegerByKey(String key, int defValue) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        int val = defValue;
        if (null != config && !TextUtils.isEmpty(config.getValue())) {
            val = Integer.valueOf(config.getValue());
        }
        return val;
    }

    @Override
    public boolean getBooleanByKey(String key, boolean defValue) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        boolean val = defValue;
        if (null != config && !TextUtils.isEmpty(config.getValue())) {
            val = Boolean.valueOf(config.getValue());
        }
        return val;
    }

    @Override
    public long getLongByKey(String key, long defValue) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        long val = defValue;
        if (null != config && !TextUtils.isEmpty(config.getValue())) {
            val = Long.valueOf(config.getValue());
        }
        return val;
    }

    @Override
    public boolean exist(String key) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        if (null == config) {
            return false;
        }
        return true;
    }

    @Override
    public void update(String key, String value) {
        getDatabase().update(getTableName(), getContentValues(key, value), Config.TABLE_COLUM_KEY + "=?",
                new String[]{key});
    }

    @Override
    public void save(String key, String value) {
        getDatabase().insert(TABLE_NAME, null, getContentValues(key, value));
    }

    protected ContentValues getContentValues(String key, String value) {
        ContentValues values = new ContentValues();
        values.put(Config.TABLE_COLUM_KEY, key);
        values.put(Config.TABLE_COLUM_VALUE, value);
        return values;
    }

    @Override
    public String getString(String key, String defValue) {
        return getValue(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return getIntegerByKey(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return getBooleanByKey(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return getLongByKey(key, defValue);
    }

    @Override
    public void putString(String key, String value) {
        putValue(key, value + "");
    }

    @Override
    public void putInt(String key, int value) {
        putValue(key, value + "");
    }

    @Override
    public void putBoolean(String key, boolean value) {
        putValue(key, value + "");
    }

    @Override
    public void putLong(String key, long value) {
        putValue(key, value + "");
    }

    private void putValue(String key, String value) {
        if (exist(key)) {
            update(key, value);
        } else {
            save(key, value);
        }
    }

    private String getValue(String key, String defValue) {
        Config config = find(ALL_COLS, Config.TABLE_COLUM_KEY + "=?", new String[]{key});
        String val = defValue;
        if (null != config && !TextUtils.isEmpty(config.getValue())) {
            val = config.getValue();
        }
        return val;
    }

    @Override
    public void remove(String key) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + Config.TABLE_COLUM_KEY + " = '" + key + "'";
        getDatabase().execSQL(sql);
    }

    @Override
    public boolean contains(String key) {
        return exist(key);
    }

    @Override
    public void clear(String... noNeedClearKeys) {
        String sql = "select * from " + TABLE_NAME;
        List<Config> list = findListBySql(sql);
        if (list == null || list.size() == 0) return;

        List<String> keys = new ArrayList<>();
        Collections.addAll(keys, noNeedClearKeys);
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i).getKey();
            if (keys.contains(key)) continue;
            remove(key);
        }
    }
}
