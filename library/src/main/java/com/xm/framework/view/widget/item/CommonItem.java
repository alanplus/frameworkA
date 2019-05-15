package com.xm.framework.view.widget.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xm.framework.R;
import com.xm.framework.base.layout.BaseRelativeLayout;
import com.xm.framework.view.widget.SwitchButton;

/**
 * Created by Mouse on 2019-05-08.
 */
public class CommonItem extends BaseRelativeLayout {


    private ImageView tagImageView, rightImageView;
    private TextView titleTextView, subTitleTextView, rightTextView;

    private View topLine, bottomLine;

    private SwitchButton switchButton;

    public CommonItem(Context context) {
        super(context);
    }

    public CommonItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getContentId() {
        return R.layout.view_common_item;
    }

    @Override
    protected void initView(AttributeSet attrs) {
        super.initView(attrs);
        tagImageView = findViewById(R.id.tag_image_view);
        rightImageView = findViewById(R.id.right_arrow);
        titleTextView = findViewById(R.id.title1);
        subTitleTextView = findViewById(R.id.title_2);
        rightTextView = findViewById(R.id.right_text);
        topLine = findViewById(R.id.top_line);
        bottomLine = findViewById(R.id.bottom_line);
        switchButton = findViewById(R.id.switch_button);
        initData(attrs);
    }

    private void initData(AttributeSet attributeSet) {
        if (null == attributeSet) {
            return;
        }
        setBackgroundResource(R.drawable.selector_item_bg);
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CommonItem);
        String title = typedArray.getString(R.styleable.CommonItem_ci_title);
        String subtitle = typedArray.getString(R.styleable.CommonItem_ci_sub_title);
        int resourceId = typedArray.getResourceId(R.styleable.CommonItem_ci_icon, 0);
        boolean isShow = typedArray.getBoolean(R.styleable.CommonItem_ci_is_show_right, true);
        String rightText = typedArray.getString(R.styleable.CommonItem_ci_right_text);
        boolean showTopLine = typedArray.getBoolean(R.styleable.CommonItem_ci_show_top_line, false);
        boolean showBottomLine = typedArray.getBoolean(R.styleable.CommonItem_ci_show_bottom_line, true);
        int showRightType = typedArray.getInt(R.styleable.CommonItem_show_right_type, 0);


        topLine.setVisibility(showTopLine ? View.VISIBLE : View.GONE);
        bottomLine.setVisibility(showBottomLine ? View.VISIBLE : View.GONE);
        rightTextView.setText(rightText);
        titleTextView.setText(title);
        subTitleTextView.setVisibility(TextUtils.isEmpty(subtitle) ? View.GONE : View.VISIBLE);
        subTitleTextView.setText(subtitle);
        if (resourceId == 0) {
            tagImageView.setVisibility(View.GONE);
        } else {
            tagImageView.setVisibility(View.VISIBLE);
            tagImageView.setImageResource(resourceId);
        }
        rightImageView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        setShowRightType(showRightType);
        typedArray.recycle();
    }

    public void setTitleText(String text) {
        this.titleTextView.setText(text);
    }

    public void setSubTitleText(String text) {
        this.subTitleTextView.setText(text);
    }

    public void setRightTextView(String text) {
        this.rightTextView.setText(text);
    }

    public void setShowRightType(int showRightType) {
        switch (showRightType) {
            case 0:
                findViewById(R.id.right_arrow_layout).setVisibility(View.GONE);
                break;
            case 1:
                switchButton.setVisibility(View.VISIBLE);
                rightImageView.setVisibility(View.GONE);
                rightTextView.setVisibility(View.GONE);
                break;
            case 2:
                switchButton.setVisibility(View.GONE);
                rightImageView.setVisibility(View.GONE);
                rightTextView.setVisibility(View.VISIBLE);
                break;
            case 3:
                switchButton.setVisibility(View.GONE);
                rightImageView.setVisibility(View.VISIBLE);
                rightTextView.setVisibility(View.GONE);
                break;
            case 4:
                switchButton.setVisibility(View.GONE);
                rightImageView.setVisibility(View.VISIBLE);
                rightTextView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
