package com.xm.file;

import com.xm.framework.base.BaseActivity;

import java.util.HashMap;

/**
 * Created by Mouse on 2019/4/2.
 */
public class MainActivity extends BaseActivity<HashMap<String, String>> {
    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void showSuccess(HashMap<String, String> stringStringHashMap) {

    }

    @Override
    public void showFailure(int code, String msg) {

    }
}
