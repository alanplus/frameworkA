package com.xm.file;

import android.view.View;

import com.xm.framework.base.BaseActivity;
import com.xm.framework.tools.InputMethodTools;
import com.xm.framework.view.widget.ClearEditText;

/**
 * Created by Mouse on 2019/4/2.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void initView() {
        final ClearEditText clearEditText = findViewById(R.id.edit_view);
        InputMethodTools.forcedShowInputMethod(clearEditText, this);
        findViewById(R.id.id2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodTools.forcedHiddenInputMethod(clearEditText, getActivity());
            }
        });

    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

}
