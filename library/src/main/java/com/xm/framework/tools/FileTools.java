package com.xm.framework.tools;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mouse on 2018/10/18.
 */
public class FileTools {

    public static String getFilename(String path) {
        if (TextUtils.isEmpty(path)) return "";
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    public static String getFileExt(String path) {
        if (path == null)
            return null;
        int index = path.lastIndexOf(".");
        if (index >= 0) {
            return path.substring(index + 1, path.length()).toLowerCase();
        }
        return null;
    }

    public static String getFilenameByKeyHashCode(String url) {
        int firstHalfLength = url.length() / 2;
        String localFilename = String.valueOf(url.substring(0, firstHalfLength).hashCode());
        localFilename += String.valueOf(url.substring(firstHalfLength).hashCode());
        return localFilename;
    }

    public static List<String> readStringList(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean exist(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        return new File(path).exists();
    }

    public static boolean move(String src, String dest, boolean delOnExist) {
        if (TextUtils.isEmpty(src) || TextUtils.isEmpty(dest) || !exist(src)) {
            return false;
        }
        File fileDest = new File(dest);
        if (!fileDest.exists()) {
            boolean mkdirs = fileDest.mkdirs();
            if (!mkdirs) return false;
        }
        if (fileDest.isFile()) return false;

        String filename = getFilename(src);
        String destFilePath = dest.endsWith("/") ? dest + filename : dest + "/" + filename;
        File destFile = new File(destFilePath);
        if (destFile.exists()) {
            if (delOnExist) {
                destFile.delete();
            } else {
                new File(src).delete();
                return true;
            }
        }
        return new File(src).renameTo(destFile);
    }

    public static String join(String path, String name) {
        if (TextUtils.isEmpty(path)) {
            return name;
        }
        return path.endsWith("/") ? path + name : path + "/" + name;
    }

    public static String getFileDir(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        int index = filePath.lastIndexOf("/");
        if (index < 0) {
            return "";
        }
        return filePath.substring(0, index + 1);
    }

    public static synchronized void copyFileFromAssets(Context context, String name, String dest) throws IOException {
        InputStream myInput = context.getAssets().open(name);
        File filePath = new File(getFileDir(dest));
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File file = new File(dest);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        OutputStream myOutput = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
