package com.xm.framework.tools;

import android.app.Activity;
import android.os.Handler;

import com.xm.framework.R;
import com.xm.framework.global.AndroidToolsConfig;
import com.xm.framework.global.LibConfig;

/**
 * Created by Mouse on 2019-06-26.
 */
public class ExitTools {


    private static ExitTools exitTools;

    protected int count = 0;

    private ExitTools() {

    }

    public static ExitTools getInstance() {
        if (null == exitTools) {
            exitTools = new ExitTools();
        }
        return exitTools;
    }

    public void exit(Activity activity) {
        if (count == 1) {
            activity.finish();
            return;
        }
        count++;
        ToastManager.getInstance().showToast(activity, AndroidTools.getResourseStr(R.string.home_exit_msg));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                count = 0;
            }
        }, AndroidTools.getResourseInteger(R.integer.home_exit_duration));
    }
}
