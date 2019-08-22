package com.xm.framework.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.xm.framework.db.annotation.ColumnAnnotation;
import com.xm.framework.db.annotation.TableAnnotation;
import com.xm.framework.db.base.BaseDAO;
import com.xm.framework.db.base.IBaseDAO;
import com.xm.framework.db.base.SQLiteManager;
import com.xm.framework.db.config.Config;
import com.xm.framework.tools.TimeTools;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * Created by Mouse on 2019-08-22.
 */
public class DBGenerator {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void generator(Class clazz) {

        try {
            generatorInterface(clazz);
            generatorClass(clazz);
        } catch (ClassNotFoundException e) {
            System.out.println("error:" + e.getException());
            e.printStackTrace();
        }
    }

    public static void generatorInterface(Class clazz) {
        String pckName = getPackageName(clazz);
        System.out.println(pckName);
        String name = clazz.getSimpleName();
        System.out.println(name);
        String iName = "I" + name + "DAO";
        TypeSpec build = TypeSpec.interfaceBuilder(iName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(IBaseDAO.class, clazz))
                .build();
        writeFile(pckName, build, clazz);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void generatorClass(Class clazz) throws ClassNotFoundException {
        String pckName = getPackageName(clazz);
        String name = clazz.getSimpleName();
        String cName = name + "DAOImpl";
        Class aClass = Class.forName(pckName + ".I" + name + "DAO");
        TypeSpec build = TypeSpec.classBuilder(cName)
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc("Automatically the code generate by Alan\nDate:" + TimeTools.getTimeStr(System.currentTimeMillis(), "yyyy-MM-dd") + "\n")
                .addField(generatorTableName(clazz))
                .addMethod(constructor())
                .addMethod(onCreate(clazz))
                .addMethod(findByCursor(clazz))
                .addMethod(getContentValues(clazz))
                .superclass(ParameterizedTypeName.get(BaseDAO.class, clazz))
                .addSuperinterface(aClass).build();
        writeFile(pckName, build, clazz);
    }

    /**
     * 写入文件
     *
     * @param pckName
     * @param typeSpec
     * @param clazz
     */
    public static void writeFile(String pckName, TypeSpec typeSpec, Class clazz) {
        JavaFile javaFile = JavaFile.builder(pckName, typeSpec).build();
        try {
            File file = new File(getDir(clazz));
            System.out.println(file.getPath());
            javaFile.writeTo(file);
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            System.out.println("error:" + e.getMessage());
        }
    }


    /**
     * 全局变量表名
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static FieldSpec generatorTableName(Class clazz) {
        TableAnnotation declaredAnnotation = (TableAnnotation) clazz.getDeclaredAnnotation(TableAnnotation.class);
        String table = "";
        if (null != declaredAnnotation) {
            table = declaredAnnotation.table();
        }
        return FieldSpec.builder(String.class, "TABLE_NAME", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL).initializer("\"" + table + "\"").build();
    }

    public static MethodSpec constructor() {
        return MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addStatement("super(TABLE_NAME)").build();
    }

    public static MethodSpec onCreate(Class clazz) {

        MethodSpec.Builder builder = MethodSpec.methodBuilder("onCreate")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(void.class)
                .addStatement("$T<String,String> hashMap = new $T<>()", HashMap.class, HashMap.class)
                .addStatement("$T<String> list = new $T<>()", List.class, ArrayList.class)
                .addParameter(SQLiteDatabase.class, "database");

        Field[] fields = clazz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            ColumnAnnotation annotation = field.getAnnotation(ColumnAnnotation.class);
            if (null != annotation) {
                builder.addStatement(String.format("hashMap.put(\"%s\",\"%s\")", annotation.name(), annotation.type()));
                if (annotation.isPrimarykey()) {
                    list.add(String.format("list.add(\"%s\")", annotation.name()));
                }
            }
        }
        for (String s : list) {
            builder.addStatement(s);
        }
        builder.addStatement("createTable(database,hashMap,list)");
        return builder.build();

    }

    public static MethodSpec findByCursor(Class clazz) {
        String s = clazz.getSimpleName().toLowerCase();
        MethodSpec.Builder builder = MethodSpec.methodBuilder("findByCursor")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(clazz)
                .addParameter(Cursor.class, "cursor");
        builder.addStatement(String.format("$T %s = new $T()", s), clazz, clazz);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ColumnAnnotation annotation = field.getAnnotation(ColumnAnnotation.class);
            if (null != annotation) {
                builder.addStatement(String.format("%s.%s=%s", s, field.getName(), getCursorStr(annotation.type(), annotation.name())));
            }
        }
        builder.addStatement("return " + s);
        return builder.build();
    }


    public static String getCursorStr(String type, String name) {
        String s = "";
        switch (type) {
            case SQLiteManager.SQLiteTable.COL_TYPE_AUTO_ID:
            case SQLiteManager.SQLiteTable.COL_TYPE_INT:
                s = "getIntFromCusor(cursor,\"" + name + "\")";
                break;
            case SQLiteManager.SQLiteTable.COL_TYPE_FLOAT:
                s = "getFloatFromCusor(cursor,\"" + name + "\")";
                break;
            case SQLiteManager.SQLiteTable.COL_TYPE_LONG:
                s = "getLongFromCusor(cursor,\"" + name + "\")";
                break;
            case SQLiteManager.SQLiteTable.COL_TYPE_TEXT:
                s = "getStringFromCusor(cursor,\"" + name + "\")";
                break;
        }
        return s;
    }

    public static MethodSpec getContentValues(Class clazz) {
        String s = clazz.getSimpleName().toLowerCase();
        MethodSpec.Builder builder = MethodSpec.methodBuilder("getContentValues")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(ContentValues.class)
                .addStatement("$T contentValues = new $T()", ContentValues.class, ContentValues.class)

                .addParameter(clazz, clazz.getSimpleName().toLowerCase());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ColumnAnnotation annotation = field.getAnnotation(ColumnAnnotation.class);
            if (null != annotation) {
                builder.addStatement(String.format("contentValues.put(\"%s\",%s.%s)", annotation.name(), s, field.getName()));
            }
        }
        builder.addStatement("return contentValues");
        return builder.build();
    }


    public static String getDir(Class clazz) {
        String string = clazz.getResource("").toString();
        int i = string.indexOf("/build/intermediates");
        string = string.substring(0, i);
        i = string.lastIndexOf("/");
        string = string.substring(i + 1);
        return string + "/src/main/java";
    }

    public static String getPackageName(Class clazz) {
        return clazz.getPackage().getName();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        String property = System.getProperty("java.version");
        System.out.println(property);
        generator(Config.class);

    }

}
