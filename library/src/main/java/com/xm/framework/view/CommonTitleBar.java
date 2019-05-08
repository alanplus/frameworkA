package com.xm.framework.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xm.framework.R;
import com.xm.framework.buttondrawable.DrawableFactory;
import com.xm.framework.tools.AndroidTools;

/**
 * Created by Mouse on 2018/6/18.
 */

public class CommonTitleBar extends RelativeLayout {

    private TextView title, rightText;
    private ImageButton imageButtonBack;

    private boolean isLight;
    private String titleStr;
    private boolean enableDivider;
    private View divider;

    private String rightTextStr;
    private int righttextSize;
    private int righttextColor;
    private int iconRes;

    @Override
    public void removeOnUnhandledKeyEventListener(OnUnhandledKeyEventListener listener) {
        super.removeOnUnhandledKeyEventListener(listener);
    }

    public CommonTitleBar(Context context) {
        this(context, null);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attributeSet) {
        isLight = false;
        enableDivider = true;
        if (null != attributeSet) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CommontTitleBarView);
            titleStr = typedArray.getString(R.styleable.CommontTitleBarView_bar_title);
            isLight = typedArray.getBoolean(R.styleable.CommontTitleBarView_is_light, false);
            enableDivider = typedArray.getBoolean(R.styleable.CommontTitleBarView_divider_enable, true);
            rightTextStr = typedArray.getString(R.styleable.CommontTitleBarView_bar_right_text);
            righttextSize = (int) typedArray.getDimension(R.styleable.CommontTitleBarView_bar_right_text_size, AndroidTools.dip2px(getContext(), 16));
            righttextColor = typedArray.getColor(R.styleable.CommontTitleBarView_bar_right_text_color, isLight ? Color.parseColor("#535353") : Color.WHITE);
            iconRes = typedArray.getResourceId(R.styleable.CommontTitleBarView_bar_right_icon, 0);
            typedArray.recycle();
        }
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_common_titlebar, this);
        title = findViewById(R.id.main_tilte);
        title.setTextColor(isLight ? Color.parseColor("#535353") : Color.WHITE);
        title.setText(titleStr);
        imageButtonBack = findViewById(R.id.icon_back);
        divider = findViewById(R.id.divider_line);
        divider.setVisibility(enableDivider ? View.VISIBLE : View.INVISIBLE);
        imageButtonBack.setImageResource(isLight ? R.drawable.icon_titlebar_back : R.drawable.icon_titlebar_back_light);
        rightText = findViewById(R.id.right_text);
        rightText.setTextColor(isLight ? Color.parseColor("#535353") : Color.WHITE);
        imageButtonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });
        initRightView();
    }

    private void initRightView() {
        if (TextUtils.isEmpty(rightTextStr) && iconRes == 0) {
            findViewById(R.id.right_view).setVisibility(View.GONE);
        } else {
            findViewById(R.id.right_view).setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(rightTextStr)) {
            findViewById(R.id.view1).setVisibility(View.GONE);
            rightText.setText(rightTextStr);
            rightText.setTextColor(righttextColor);
            rightText.setTextSize(righttextSize);
            rightText.setVisibility(View.VISIBLE);
            return;
        }

        if (iconRes != 0) {
            findViewById(R.id.icon1).setBackgroundResource(iconRes);
            rightText.setVisibility(View.GONE);
            findViewById(R.id.view1).setVisibility(View.VISIBLE);
        }
    }


    public void setOnRightViewClickListener(OnClickListener onRightViewClickListener) {
        findViewById(R.id.right_view).setOnClickListener(onRightViewClickListener);
    }

    public void showRightText(String text, OnClickListener onClickListener) {
        this.rightTextStr = text;
        initRightView();
        setOnRightViewClickListener(onClickListener);
    }

    public void setRightTextSize(int textSize) {
        this.righttextSize = textSize;
        rightText.setTextSize(righttextSize);
    }

    public void setRighttextColor(@ColorInt int color) {
        this.righttextColor = color;
        rightText.setTextColor(color);
    }


    public void setTitle(String text) {
        title.setText(text);
    }

    public void setTitle(String text, OnClickListener onClickListener) {
        setTitle(text);
        title.setOnClickListener(onClickListener);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getRightText() {
        return rightText;
    }

    public ImageButton getImageButtonBack() {
        return imageButtonBack;
    }

    public void showDivider(boolean show) {
        divider.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}
