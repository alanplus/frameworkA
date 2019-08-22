package com.xm.framework.db.annotation;

import com.xm.framework.db.base.SQLiteManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mouse on 2019-08-22.
 */

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ColumnAnnotation {
    String name();

    String type() default SQLiteManager.SQLiteTable.COL_TYPE_TEXT;

    boolean isPrimarykey() default false;
}
