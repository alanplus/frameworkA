package com.xm.framework.db.config;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xm.framework.db.base.BaseDAO;
import com.xm.framework.tools.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Automatically the code generate by Alan
 * Date:2019-08-26
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


    @Override
    public Config getConfig(String key) {
        return findBySql(String.format("select * from %s where key='%s'", TABLE_NAME, key));
    }

    @Override
    public void clear(String... keys) {
        StringBuilder stringBuilder = new StringBuilder("delete from ");
        stringBuilder.append(TABLE_NAME);
        stringBuilder.append(" where key not in (");

        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i]);
            stringBuilder.append(i == keys.length - 1 ? ")" : ",");
        }
        Logger.d(stringBuilder.toString());
        getDatabase().execSQL(stringBuilder.toString());
    }


    @Override
    public void remove(String key) {
        getDatabase().delete(TABLE_NAME, "key=?", args(key));
    }

}
