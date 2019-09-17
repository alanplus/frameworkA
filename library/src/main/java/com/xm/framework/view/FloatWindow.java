package com.xm.framework.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.xm.framework.tools.AndroidTools;

public class FloatWindow {

    private WindowManager.LayoutParams layoutParams;
    private View view;
    private WindowManager windowManager;

    public FloatWindow() {
        layoutParams = new WindowManager.LayoutParams();
        if (AndroidTools.getCurrentSdk() < Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public FloatWindow setType(int type) {
        layoutParams.type = type;
        return this;
    }

    public FloatWindow setGravity(int gravity) {
        layoutParams.gravity = gravity;
        return this;
    }

    public FloatWindow setStart(int x, int y) {
        layoutParams.x = x;
        layoutParams.y = y;
        return this;
    }

    public FloatWindow setFlag(int flag) {
        layoutParams.flags = flag;
        return this;
    }

    public FloatWindow setFormat(int format) {
        layoutParams.format = format;
        return this;
    }

    public FloatWindow setSize(int width, int height) {
        layoutParams.width = width;
        layoutParams.height = height;
        return this;
    }

    public FloatWindow setContentView(View view) {
        this.view = view;
        return this;
    }

    public FloatWindow show(Context context) {
        if (null != view && layoutParams != null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.addView(view, layoutParams);
        }
        return this;
    }

    public void hidden() {
        if (windowManager != null && view != null) {
//            windowManager.removeViewImmediate(view);
            windowManager.removeView(view);
            windowManager = null;
        }
    }

    public boolean isShow() {
        return windowManager != null;
    }

    public boolean canShowWindowAndGo(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            Toast.makeText(context, "当前无权限，请授权", Toast.LENGTH_SHORT);
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, 0);
            return false;
        }
        return true;
    }

}
