package com.xm.framework.tools;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Mouse on 2019-05-18.
 */
public class InputMethodTools {

    public static void changeInputMethodState(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void forcedShowInputMethod(final EditText editText, final Context context) {
        /**
         * 1. view 必须是EditText
         * 2. EditText 必须是可以获得焦点的
         * 3. EditText 必须已经获得焦点
         * 4. EditText 必须可见
         * 5. 当前布局必须已经完成加载
         * 第二个参数 可以为0 InputMethod.SHOW_FORCE
         * 如果不为0的话 隐藏方法有的时候会不好用
         * https://www.cnblogs.com/ldq2016/p/6861128.html
         */
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
            }
        }, 100);
    }

    public static void forcedHiddenInputMethod(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isInputMethodActive(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();//isOpen若返回true，则表示输入法打开
    }
}
