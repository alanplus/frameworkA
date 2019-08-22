package com.xm.framework.db.config;

import com.xm.framework.db.annotation.ColumnAnnotation;
import com.xm.framework.db.annotation.TableAnnotation;
import com.xm.framework.db.base.SQLiteManager;

import java.io.Serializable;

@TableAnnotation(table = "config")
public class Config implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4425742063974628222L;

    @ColumnAnnotation(name = "key", type = SQLiteManager.SQLiteTable.COL_TYPE_TEXT, isPrimarykey = true)
    public String key;
    @ColumnAnnotation(name = "value", type = SQLiteManager.SQLiteTable.COL_TYPE_TEXT)
    public String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final String TABLE_COLUM_ID = "id";
    public static final String TABLE_COLUM_KEY = "key";
    public static final String TABLE_COLUM_VALUE = "value";
}
