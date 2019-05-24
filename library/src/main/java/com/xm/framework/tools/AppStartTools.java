package com.xm.framework.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Mouse on 2019-05-24.
 */
public class AppStartTools {

    public static final String TYPE_3GP = ".3gp";
    public static final String TYPE_APK = ".apk";
    public static final String TYPE_ASF = ".asf";
    public static final String TYPE_AVI = ".avi";
    public static final String TYPE_bin = ".bin";
    public static final String TYPE_BMP = ".bmp";
    public static final String TYPE_C = ".c";
    public static final String TYPE_CLASS = ".class";
    public static final String TYPE_CONF = ".conf";
    public static final String TYPE_CPP = ".cpp";
    public static final String TYPE_DOC = ".doc";
    public static final String TYPE_EXE = ".exe";
    public static final String TYPE_GIF = ".gif";
    public static final String TYPE_GTAR = ".gtar";
    public static final String TYPE_GZ = ".gz";
    public static final String TYPE_H = ".h";
    public static final String TYPE_HTM = ".htm";
    public static final String TYPE_HTML = ".html";
    public static final String TYPE_JAR = ".jar";
    public static final String TYPE_JAVA = ".java";
    public static final String TYPE_JPEG = ".jpeg";
    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_JS = ".js";
    public static final String TYPE_LOG = ".log";
    public static final String TYPE_M3U = ".m3u";
    public static final String TYPE_M4A = ".m4a";
    public static final String TYPE_M4B = ".m4b";
    public static final String TYPE_M4P = ".m4p";
    public static final String TYPE_M4U = ".m4u";
    public static final String TYPE_M4V = ".m4v";
    public static final String TYPE_MOV = ".mov";
    public static final String TYPE_MP2 = ".mp2";
    public static final String TYPE_MP3 = ".mp3";
    public static final String TYPE_MP4 = ".mp4";
    public static final String TYPE_MPC = ".mpc";
    public static final String TYPE_MPE = ".mpe";
    public static final String TYPE_MPEG = ".mpeg";
    public static final String TYPE_MPG = ".mpg";
    public static final String TYPE_MPG4 = ".mpg4";
    public static final String TYPE_MPGA = ".mpga";
    public static final String TYPE_MSG = ".msg";
    public static final String TYPE_OGG = ".ogg";
    public static final String TYPE_PDF = ".pdf";
    public static final String TYPE_PNG = ".png";
    public static final String TYPE_PPS = ".pps";
    public static final String TYPE_PPT = ".ppt";
    public static final String TYPE_PROP = ".prop";
    public static final String TYPE_RAR = ".rar";
    public static final String TYPE_RC = ".rc";
    public static final String TYPE_RMVB = ".rmvb";
    public static final String TYPE_RTF = ".rtf";
    public static final String TYPE_SH = ".sh";
    public static final String TYPE_TAR = ".tar";
    public static final String TYPE_TGZ = ".tgz";
    public static final String TYPE_TXT = ".txt";
    public static final String TYPE_WAV = ".wav";
    public static final String TYPE_WMA = ".wma";
    public static final String TYPE_WMV = ".wmv";
    public static final String TYPE_WPS = ".wps";
    public static final String TYPE_XML = ".xml";
    public static final String TYPE_Z = ".z";
    public static final String TYPE_ZIP = ".zip";


    public static final String FILE_TYPE_VIDEO_3GP = "video/3gpp";
    public static final String FILE_TYPE_VIDEO_MS = "video/x-msvideo";
    public static final String FILE_TYPE_VIDEO_ASF = "video/x-ms_asf";
    public static final String FILE_TYPE_VIDEO_VND = "video/vnd.mpegurl";
    public static final String FILE_TYPE_VIDEO_M4V = "video/x-m4v";
    public static final String FILE_TYPE_VIDEO_QUICKTIME = "video/quicktime";
    public static final String FILE_TYPE_VIDEO_MP4 = "video/mp4";
    public static final String FILE_TYPE_VIDEO_MPEG = "video/mpeg";


    public static final String FILE_TYPE_APPLICATION_VDN = "application/vnd.android.package-archive";
    public static final String FILE_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String FILE_TYPE_APPLICATION_MSWORD = "application/msword";
    public static final String FILE_TYPE_APPLICATION_ZIP = "application/zip";
    public static final String FILE_TYPE_APPLICATION_COMPRESS = "application/x-compress";
    public static final String FILE_TYPE_APPLICATION_VDN_WORKS = "application/vnd.ms-works";
    public static final String FILE_TYPE_APPLICATION_COMPRESSED = "application/x-compressed";
    public static final String FILE_TYPE_APPLICATION_RAR_COMPRESSED = "application/x-rar-compressed";
    public static final String FILE_TYPE_APPLICATION_GTAR = "application/x-gtar";
    public static final String FILE_TYPE_APPLICATION_PDF = "application/pdf";
    public static final String FILE_TYPE_APPLICATION_TAR = "application/x-tar";
    public static final String FILE_TYPE_APPLICATION_RTF = "application/rtf";
    public static final String FILE_TYPE_APPLICATION_GZIP = "application/x-gzip";
    public static final String FILE_TYPE_APPLICATION_JAVA = "application/java-archive";
    public static final String FILE_TYPE_APPLICATION_JAVASCRIPT = "application/x-javascript";
    public static final String FILE_TYPE_APPLICATION_VDN_CERTIFICATE = "application/vnd.mpohun.certificate";
    public static final String FILE_TYPE_APPLICATION_VDN_OUTLOOK = "application/vnd.ms-outlook";
    public static final String FILE_TYPE_APPLICATION_VDN_POWERPOINT = "application/vnd.ms-powerpoint";

    public static final String FILE_TYPE_IMAGE_BMP = "image/bmp";
    public static final String FILE_TYPE_IMAGE_GIF = "image/gif";
    public static final String FILE_TYPE_IMAGE_PNG = "image/png";
    public static final String FILE_TYPE_IMAGE_JPEG = "image/jpeg";


    public static final String FILE_TYPE_TEXT_PLAIN = "text/plain";
    public static final String FILE_TYPE_TEXT_HTML = "text/html";

    public static final String FILE_TYPE_AUDIO_MPEGURL = "audio/x-mpegurl";
    public static final String FILE_TYPE_AUDIO_MP4A_LATM = "audio/mp4a-latm";
    public static final String FILE_TYPE_AUDIO_X_MPEG = "audio/x-mpeg";
    public static final String FILE_TYPE_AUDIO_OGG = "audio/ogg";
    public static final String FILE_TYPE_AUDIO_MPEG = "audio/mpeg";
    public static final String FILE_TYPE_AUDIO_REALAUDIO = "audio/x-pn-realaudio";
    public static final String FILE_TYPE_AUDIO_WAV = "audio/x-wav";
    public static final String FILE_TYPE_AUDIO_WMA = "audio/x-ms-wma";
    public static final String FILE_TYPE_AUDIO_WMV = "audio/x-ms-wmv";


    public static HashMap<String, String> map = new HashMap<>();


    static {

        map.put(TYPE_3GP, FILE_TYPE_VIDEO_3GP);
        map.put(TYPE_ASF, FILE_TYPE_VIDEO_ASF);
        map.put(TYPE_AVI, FILE_TYPE_VIDEO_MS);
        map.put(TYPE_M4U, FILE_TYPE_VIDEO_VND);
        map.put(TYPE_M4V, FILE_TYPE_VIDEO_M4V);
        map.put(TYPE_MOV, FILE_TYPE_VIDEO_QUICKTIME);
        map.put(TYPE_MP4, FILE_TYPE_VIDEO_MP4);
        map.put(TYPE_MPE, FILE_TYPE_VIDEO_MPEG);
        map.put(TYPE_MPEG, FILE_TYPE_VIDEO_MPEG);
        map.put(TYPE_MPG, FILE_TYPE_VIDEO_MPEG);
        map.put(TYPE_MPG4, FILE_TYPE_VIDEO_MP4);

        map.put(TYPE_M3U, FILE_TYPE_AUDIO_MPEGURL);
        map.put(TYPE_M4A, FILE_TYPE_AUDIO_MP4A_LATM);
        map.put(TYPE_M4B, FILE_TYPE_AUDIO_MP4A_LATM);
        map.put(TYPE_M4P, FILE_TYPE_AUDIO_MP4A_LATM);
        map.put(TYPE_MP2, FILE_TYPE_AUDIO_X_MPEG);
        map.put(TYPE_MP3, FILE_TYPE_AUDIO_X_MPEG);
        map.put(TYPE_MPGA, FILE_TYPE_AUDIO_MPEG);
        map.put(TYPE_OGG, FILE_TYPE_AUDIO_OGG);
        map.put(TYPE_RMVB, FILE_TYPE_AUDIO_REALAUDIO);
        map.put(TYPE_WAV, FILE_TYPE_AUDIO_WAV);
        map.put(TYPE_WMA, FILE_TYPE_AUDIO_WMA);
        map.put(TYPE_WMV, FILE_TYPE_AUDIO_WMV);

        map.put(TYPE_APK, FILE_TYPE_APPLICATION_VDN);
        map.put(TYPE_bin, FILE_TYPE_APPLICATION_OCTET_STREAM);
        map.put(TYPE_CLASS, FILE_TYPE_APPLICATION_OCTET_STREAM);
        map.put(TYPE_DOC, FILE_TYPE_APPLICATION_MSWORD);
        map.put(TYPE_EXE, FILE_TYPE_APPLICATION_OCTET_STREAM);
        map.put(TYPE_GTAR, FILE_TYPE_APPLICATION_GTAR);
        map.put(TYPE_GZ, FILE_TYPE_APPLICATION_GZIP);
        map.put(TYPE_JAR, FILE_TYPE_APPLICATION_JAVA);
        map.put(TYPE_JS, FILE_TYPE_APPLICATION_JAVASCRIPT);
        map.put(TYPE_MPC, FILE_TYPE_APPLICATION_VDN_CERTIFICATE);
        map.put(TYPE_MSG, FILE_TYPE_APPLICATION_VDN_OUTLOOK);
        map.put(TYPE_PDF, FILE_TYPE_APPLICATION_PDF);
        map.put(TYPE_PPS, FILE_TYPE_APPLICATION_VDN_POWERPOINT);
        map.put(TYPE_PPT, FILE_TYPE_APPLICATION_VDN_POWERPOINT);
        map.put(TYPE_RAR, FILE_TYPE_APPLICATION_RAR_COMPRESSED);
        map.put(TYPE_RTF, FILE_TYPE_APPLICATION_RTF);
        map.put(TYPE_TAR, FILE_TYPE_APPLICATION_TAR);
        map.put(TYPE_TGZ, FILE_TYPE_APPLICATION_COMPRESSED);
        map.put(TYPE_WPS, FILE_TYPE_APPLICATION_VDN_WORKS);
        map.put(TYPE_Z, FILE_TYPE_APPLICATION_COMPRESS);
        map.put(TYPE_ZIP, FILE_TYPE_APPLICATION_ZIP);

        map.put(TYPE_BMP, FILE_TYPE_IMAGE_BMP);
        map.put(TYPE_GIF, FILE_TYPE_IMAGE_GIF);
        map.put(TYPE_JPEG, FILE_TYPE_IMAGE_JPEG);
        map.put(TYPE_JPG, FILE_TYPE_IMAGE_JPEG);
        map.put(TYPE_PNG, FILE_TYPE_IMAGE_PNG);


        map.put(TYPE_C, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_CONF, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_CPP, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_H, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_HTM, FILE_TYPE_TEXT_HTML);
        map.put(TYPE_HTML, FILE_TYPE_TEXT_HTML);
        map.put(TYPE_JAVA, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_LOG, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_PROP, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_RC, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_SH, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_TXT, FILE_TYPE_TEXT_PLAIN);
        map.put(TYPE_XML, FILE_TYPE_TEXT_PLAIN);
    }

    /**
     * 根据路径打开文件
     * 需在AndroidManifest.xml 中添加下列代码
     * 主要解决：android 7.0 无法使用文件的问题
     * <provider
     * android:name="android.support.v4.content.FileProvider"
     * android:authorities="com.alan.file.manager.provider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/provider_paths"/>
     * </provider>
     *
     * @param context 上下文
     * @param path    文件路径
     */

    public static void startAppByPath(Context context, String path) {

        if (context == null || path == null) {
            return;
        }
        String fileExt = "." + FileTools.getFileExt(path);
        if (map.containsKey(fileExt)) {
            String fileType = map.get(fileExt);
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri;
                if (AndroidTools.getCurrentSdk() >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(path));

                } else {
                    uri = Uri.fromFile(new File(path));
                }
                intent.setDataAndType(uri, fileType);
                context.startActivity(intent);
            } catch (Exception e) {
                ToastManager.getInstance().showToast(context, "无法打开该格式文件!");
            }
        } else {
            ToastManager.getInstance().showToast(context, "无法打开该格式文件!");
        }
    }
}
