package com.xm.framework.db.config;

import com.xm.framework.db.annotation.ColumnAnnotation;
import com.xm.framework.db.annotation.TableAnnotation;

import java.io.Serializable;

@TableAnnotation(table = "config")
public class Config implements Serializable {

    public Config() {

    }

    public Config(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     *
     */
    private static final long serialVersionUID = 4425742063974628222L;

    @ColumnAnnotation(name = "key", isPrimarykey = true)
    public String key;
    @ColumnAnnotation(name = "value")
    public String value;


}
