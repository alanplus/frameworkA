package com.xm.file;

import android.support.v4.app.Fragment;
import android.view.View;

import com.xm.framework.base.BaseActivity;
import com.xm.framework.base.activity.HomeBaseActivity;
import com.xm.framework.tools.InputMethodTools;
import com.xm.framework.view.widget.ClearEditText;

/**
 * Created by Mouse on 2019/4/2.
 */
public class MainActivity extends HomeBaseActivity {

    @Override
    protected void initView() {
        super.initView();
        showMsgHitView(3);
    }

    @Override
    protected Fragment[] getFragmentArray() {
        return new Fragment[0];
    }
}
