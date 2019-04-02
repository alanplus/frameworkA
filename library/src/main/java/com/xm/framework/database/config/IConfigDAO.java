package com.xm.framework.database.config;


import com.xm.framework.database.IBaseDAO;

public interface IConfigDAO extends IBaseDAO<Config> {

    String getStringByKey(String key, String defValue);

    int getIntegerByKey(String key, int defValue);

    boolean getBooleanByKey(String key, boolean defValue);

    long getLongByKey(String key, long defValue);

    void setValue(String key, String val);

    void setValue(String key, int val);

    void setValue(String key, boolean val);

    void setValue(String key, long val);

    boolean exist(String key);

    void update(String key, String value);

    void save(String key, String value);

    void remove(String key);

    boolean contains(String key);

    String getString(String key, String defValue);

    int getInt(String key, int defValue);

    boolean getBoolean(String key, boolean defValue);

    long getLong(String key, long defValue);

    void putString(String key, String value);

    void putInt(String key, int value);

    void putBoolean(String key, boolean value);

    void putLong(String key, long value);

    void clear(String... noNeedClearKeys);
}
