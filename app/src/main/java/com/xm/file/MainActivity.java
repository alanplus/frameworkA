package com.xm.file;

import com.xm.framework.base.BaseActivity;
import com.xm.framework.global.AndroidToolsConfig;
import com.xm.framework.net.ApiConfig;
import com.xm.framework.net.ApiRequest;
import com.xm.framework.net.ApiResult;
import com.xm.framework.tools.Logger;

import java.util.HashMap;

/**
 * Created by Mouse on 2019/4/2.
 */
public class MainActivity extends BaseActivity<HashMap<String, String>> {
    @Override
    protected int getContentId() {
        int color = AndroidToolsConfig.androidToolsConfig.getStatusBarColor();
        Logger.d("data:" + color);
        ApiResult apiResult = new ApiRequest(new ApiConfig() {
            @Override
            public String url() {
                return null;
            }

            @Override
            public HashMap<String, String> params() {
                return null;
            }

            @Override
            public ApiResult onResultCallback(String text) {
                return null;
            }

            @Override
            public void onOtherErrorCallback(int code) {

            }
        }).executeByGet();
        return R.layout.activity_main;
    }

    @Override
    public void showSuccess(HashMap<String, String> stringStringHashMap) {

    }

    @Override
    public void showFailure(int code, String msg) {

    }
}
