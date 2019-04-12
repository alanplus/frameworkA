package com.xm.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.xm.framework.R;

/**
 * Created by Mouse on 2019/3/21.
 */
public class WithMaxHeightScrollView extends ScrollView {

    private float maxHeight;

    public WithMaxHeightScrollView(Context context) {
        super(context);
    }

    public WithMaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WithMaxHeightScrollView);
            maxHeight = typedArray.getDimension(R.styleable.WithMaxHeightScrollView_max_height, 0f);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = maxHeight != 0 ? (int) maxHeight : heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
