package com.xm.file.global;

import android.app.Application;

import com.xm.framework.global.AndroidToolsConfig;
import com.xm.framework.global.LibConfig;

/**
 * Created by Mouse on 2019/4/2.
 */
public class FileApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LibConfig.init(new FileAndroidToolsConfig(this));
    }
}
