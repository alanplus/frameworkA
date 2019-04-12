package com.xm.framework.base.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xm.framework.R;
import com.xm.framework.tools.ViewTools;


/**
 * Created by Mouse on 2019/3/21.
 */
public class TipDialog extends BaseDialog {

    private TextView leftTextView, midTextView, rightTextView, titleTextView, contentTextView;
    private View titleLine, leftLine, centerLine;
    private Params params;

    public TipDialog(Context context) {
        super(context);
    }

    public TipDialog(@NonNull Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    public TipDialog(@NonNull Context context, int style, OnBaseDialogListener onBaseDialogListener) {
        super(context, style, onBaseDialogListener);
    }

    @Override
    public int getContentRes() {
        return R.layout.dialog_matier_tip;
    }

    @Override
    public void initView() {
        leftTextView = findViewById(R.id.negativeButton);
        midTextView = findViewById(R.id.centerButton);
        rightTextView = findViewById(R.id.positiveButton);
        titleTextView = findViewById(R.id.title_view);
        contentTextView = findViewById(R.id.content);
        titleLine = findViewById(R.id.title_line);
        leftLine = findViewById(R.id.left_line);
        centerLine = findViewById(R.id.center_line);
        setSize(0.8);
        setData();
    }


    public void setData() {
        setCancelable(params.cancelAble);
        setTextViewVisiable(params.mTitle, titleTextView, titleLine);
        contentTextView.setText(params.mMessage);
        setTextViewVisiable(params.mNeutralButtonText, leftTextView, leftLine);
        setTextViewVisiable(params.mCenterButtonText, midTextView, centerLine);
        setTextViewVisiable(params.mPositiveButtonText, rightTextView, null);
        setViewOnClickListener(leftTextView, params.mNeutralButtonListener, params.autoDismiss);
        setViewOnClickListener(midTextView, params.mCenterButtonListener, params.autoDismiss);
        setViewOnClickListener(rightTextView, params.mPositiveButtonListener, params.autoDismiss);
        leftTextView.setTextColor(params.mBtnLeftColorRes);
        midTextView.setTextColor(params.mBtnMidColorRes);
        rightTextView.setTextColor(params.mBtnRightColorRes);
        contentTextView.setGravity(params.gravity);

    }

    public void setParams(Params params) {
        this.params = params;
    }

    public void setViewOnClickListener(View view, final DialogInterface.OnClickListener onClickListener, final boolean isAutoDismiss) {

        ViewTools.setClickEffection(view);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (null == onClickListener) {
                    dismiss();
                    return;
                }
                onClickListener.onClick(TipDialog.this, 0);
                if (isAutoDismiss) {
                    dismiss();
                }
            }
        });
    }

    public void setTextViewVisiable(String text, TextView textView, View view) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            if (null != view) {
                view.setVisibility(View.GONE);
            }
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
            if (null != view) {
                view.setVisibility(View.VISIBLE);
            }
        }

    }

    public static class Builder {
        private Params mBp;

        public Builder(Context context) {
            mBp = new Params();
            mBp.mContext = context;
        }

        public Builder setTitle(String title) {
            mBp.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mBp.mMessage = message;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener) {
            mBp.mPositiveButtonText = positiveButtonText;
            mBp.mPositiveButtonListener = positiveButtonListener;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            mBp.mPositiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setPositiveButtonListener(DialogInterface.OnClickListener positiveButtonListener) {
            mBp.mPositiveButtonListener = positiveButtonListener;
            return this;
        }


        public Builder setNeutralButton(String neutralButtonText, DialogInterface.OnClickListener neutralButtonListener) {
            mBp.mNeutralButtonText = neutralButtonText;
            mBp.mNeutralButtonListener = neutralButtonListener;
            return this;
        }

        public Builder setCenterButton(String centerButtonText, DialogInterface.OnClickListener centerButtonListener) {
            mBp.mCenterButtonText = centerButtonText;
            mBp.mCenterButtonListener = centerButtonListener;
            return this;
        }

        public Builder setButtonRightTextColor(@ColorInt int color) {
            mBp.mBtnRightColorRes = color;
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            mBp.gravity = gravity;
            return this;
        }

        public Builder setButtonLeftTextColor(@ColorInt int color) {
            mBp.mBtnLeftColorRes = color;
            return this;
        }

        public Builder setButtonMidTextColor(@ColorInt int color) {
            mBp.mBtnMidColorRes = color;
            return this;
        }

        public Builder setNeutralButtonText(String neutralButtonText) {
            mBp.mNeutralButtonText = neutralButtonText;
            return this;
        }

        public Builder setNeutralButtonListener(DialogInterface.OnClickListener neutralButtonListener) {
            mBp.mNeutralButtonListener = neutralButtonListener;
            return this;
        }

        public Builder setCenterButtonText(String centerButtonText) {
            mBp.mCenterButtonText = centerButtonText;
            return this;
        }

        public Builder setCenterButtonListener(DialogInterface.OnClickListener centerButtonListener) {
            mBp.mCenterButtonListener = centerButtonListener;
            return this;
        }

        private TipDialog onCreate() {
            final TipDialog dialog = new TipDialog(mBp.mContext);
            return dialog;
        }

        public TipDialog show() {
            TipDialog dialog = onCreate();
            dialog.setParams(mBp);
            dialog.show();
            return dialog;
        }

        public Builder setCancelable(boolean able) {
            mBp.cancelAble = able;
            return this;
        }

        public Builder setAutoDismiss(boolean auto) {
            mBp.autoDismiss = auto;
            return this;
        }
    }

    private static class Params {
        Context mContext;
        String mTitle;
        String mMessage;
        String mPositiveButtonText;
        DialogInterface.OnClickListener mPositiveButtonListener;
        String mNeutralButtonText;
        DialogInterface.OnClickListener mNeutralButtonListener;
        String mCenterButtonText;
        DialogInterface.OnClickListener mCenterButtonListener;
        @ColorInt
        int mBtnRightColorRes = Color.parseColor("#2b2b2b");
        @ColorInt
        int mBtnLeftColorRes = Color.parseColor("#2b2b2b");
        @ColorInt
        int mBtnMidColorRes = Color.parseColor("#2b2b2b");
        int gravity = Gravity.CENTER;

        boolean cancelAble = true;
        boolean autoDismiss = true;
    }
}
