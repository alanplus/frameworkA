package com.xm.framework.database.config;

import java.io.Serializable;

public class Config implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4425742063974628222L;

    private String key;
    private String value;

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
