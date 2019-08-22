package com.xm.framework.db.config;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xm.framework.db.base.BaseDAO;

import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Automatically the code generate by Alan
 * Date:2019-08-22
 */
public class ConfigDAOImpl extends BaseDAO<Config> implements IConfigDAO {
    private static final String TABLE_NAME = "config";

    public ConfigDAOImpl() {
        super(TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        HashMap<String, String> hashMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        hashMap.put("key", "TEXT");
        hashMap.put("value", "TEXT");
        list.add("key");
        createTable(database, hashMap, list);
    }

    @Override
    public Config findByCursor(Cursor cursor) {
        Config config = new Config();
        config.key = getStringFromCusor(cursor, "key");
        config.value = getStringFromCusor(cursor, "value");
        return config;
    }

    @Override
    public ContentValues getContentValues(Config config) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", config.key);
        contentValues.put("value", config.value);
        return contentValues;
    }
}
