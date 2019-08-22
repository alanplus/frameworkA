package com.xm.framework.db;

import android.content.Context;

import com.xm.framework.db.base.DatabaseConfig;
import com.xm.framework.db.base.SQLiteManager;

import java.util.List;

/**
 * Created by Mouse on 2019-08-21.
 */
public abstract class BaseDBModel implements SQLiteManager.SQLiteTable {

    public static final String[] ALL_COLS = new String[]{"*"};

    protected Context mContext;
    private DatabaseConfig mConfig;
    private List<Class<? extends BaseDBModel>> mPatcherList;

    public BaseDBModel() {

    }

    public BaseDBModel(Context context, DatabaseConfig databaseConfig) {
        this.mContext = context.getApplicationContext();
        this.mConfig = databaseConfig;
    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database) {
//        Field[] fields = getClass().getDeclaredFields();
//        StringBuilder stringBuilder = new StringBuilder();
//        StringBuilder primaryStr = new StringBuilder();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            ModelAnnotation annotation = field.getAnnotation(ModelAnnotation.class);
//            if (null != annotation) {
//                stringBuilder.append(annotation.name());
//                stringBuilder.append(" ");
//                stringBuilder.append(annotation.type());
//                stringBuilder.append(",");
//                if (annotation.isPrimarykey()) {
//                    primaryStr.append(annotation.name());
//                    primaryStr.append(",");
//                }
//            }
//        }
//        if (primaryStr.length() > 0) {
//            primaryStr.deleteCharAt(primaryStr.length() - 1);
//            stringBuilder.append("PRIMARY KEY (");
//            stringBuilder.append(primaryStr.toString());
//            stringBuilder.append(")");
//        }
//
//        createTable(database, stringBuilder.toString());
//    }
//
//    @Override
//    public void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion) {
//
//    }
//
//    /**
//     * 创建表
//     *
//     * @param database
//     * @param content
//     */
//    private void createTable(SQLiteDatabase database, String content) {
//        String sqlFormat = "CREATE TABLE %s (%s)";
//        String sql = String.format(sqlFormat, getTableName(), content);
//        Logger.d("db_sql", sql);
//        database.execSQL(sql);
//    }
//
//    public abstract String getTableName();
//
//    public SQLiteDatabase getDatabase() {
//        return SQLiteManager.getDB(mContext, mConfig);
//    }
//
//    public void save() {
//        getDatabase().insert(getTableName(), null, getContentValues());
//    }
//
//    public void replace() {
//        getDatabase().replace(getTableName(), null, getContentValues());
//    }
//
//    public void deleteAll() {
//        getDatabase().delete(getTableName(), null, null);
//    }
//
//    public void update() {
//        String[][] onlyWhereSel = getOnlyWhereSel();
//        if (onlyWhereSel[0].length == 0) {
//            return;
//        }
//        getDatabase().update(getTableName(), getContentValues(), buildWhereSel(onlyWhereSel[0]), onlyWhereSel[1]);
//    }
//
//
//    private ContentValues getContentValues() {
//        Field[] fields = getClass().getDeclaredFields();
//        ContentValues contentValues = new ContentValues();
//        try {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                ModelAnnotation annotation = field.getAnnotation(ModelAnnotation.class);
//                if (null != annotation) {
//                    switch (annotation.type()) {
//                        case SQLiteManager.SQLiteTable.COL_TYPE_AUTO_ID:
//                        case SQLiteManager.SQLiteTable.COL_TYPE_INT:
//                            contentValues.put(annotation.name(), (int) field.get(this));
//                            break;
//                        case SQLiteManager.SQLiteTable.COL_TYPE_FLOAT:
//                            contentValues.put(annotation.name(), (float) field.get(this));
//                            break;
//                        case SQLiteManager.SQLiteTable.COL_TYPE_TEXT:
//                            contentValues.put(annotation.name(), (String) field.get(this));
//                            break;
//                        case SQLiteManager.SQLiteTable.COL_TYPE_LONG:
//                            contentValues.put(annotation.name(), (Long) field.get(this));
//                            break;
//                    }
//                }
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return contentValues;
//    }
//
//    private String[][] getOnlyWhereSel() {
//        Field[] fields = getClass().getDeclaredFields();
//        String[][] array = new String[2][];
//        int count = 0;
//        try {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                ModelAnnotation annotation = field.getAnnotation(ModelAnnotation.class);
//                if (null != annotation && annotation.isPrimarykey()) {
//                    array[0][count] = annotation.name();
//                    array[1][count] = String.valueOf(field.get(this));
//                    count++;
//                }
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return array;
//    }
//
//    private String buildWhereSel(String[] whereStr) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < whereStr.length; i++) {
//            sb.append(whereStr[i]);
//            sb.append("=?");
//            if (i != whereStr.length - 1) {
//                sb.append(" and ");
//            }
//        }
//        return sb.toString();
//    }
//

}
