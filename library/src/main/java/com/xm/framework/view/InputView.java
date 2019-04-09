package com.xm.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.xm.framework.tools.AndroidTools;

/**
 * Created by Mouse on 2018/5/14.
 */

public class InputView extends android.support.v7.widget.AppCompatTextView{

    public boolean hasLine;
    public int color;

    public InputView(Context context) {
        super(context);
    }

    public InputView(Context context, boolean hasLine) {
        super(context);
        this.hasLine = hasLine;
        color = Color.WHITE;
    }
    public InputView(Context context, boolean hasLine, int color) {
        super(context);
        this.hasLine = hasLine;
        this.color = color;
    }

    public InputView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    public void setLineColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(hasLine){
            int measuredHeight = getMeasuredHeight();
            int measuredWidth = getMeasuredWidth();
            TextPaint paint = getPaint();
            paint.setColor(color);
            paint.setStrokeWidth(AndroidTools.dip2px(getContext(),1));
            canvas.drawLine(0,measuredHeight-1,measuredWidth,measuredHeight-1,paint);
        }
    }

    public boolean canInput(){
        return hasLine;
    }

    public boolean hasValue(){
        CharSequence text = getText();
        return !TextUtils.isEmpty(text);
    }
}
