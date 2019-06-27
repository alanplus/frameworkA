package com.xm.framework.base.recycle;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.xm.framework.R;
import com.xm.framework.base.layout.BaseRelativeLayout;

/**
 * Created by Mouse on 2019-06-26.
 */
public class FootView extends BaseRelativeLayout {

    private View loadingView, failureView, endView;

    public static final int Normal = 0;
    public static final int Loading = 1;
    public static final int TheEnd = 2;
    public static final int NetWorkError = 3;

    @IntDef({Normal, Loading, TheEnd, NetWorkError})
    @interface FooterState {
    }

    public FootView(Context context) {
        super(context);
    }

    public FootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getContentId() {
        return R.layout.view_foot;
    }

    @Override
    protected void initView(AttributeSet attrs) {
        super.initView(attrs);
        loadingView = findViewById(R.id.loading_viewstub);
        failureView = findViewById(R.id.network_error_viewstub);
        endView = findViewById(R.id.end_viewstub);
    }

    //根据传过来的status控制哪个状态可见
    public void setData(@FooterState int status) {
        switch (status) {
            case Normal:
                setAllGone();
                break;
            case Loading:
                setExceptGone(loadingView);
                break;
            case TheEnd:
                setExceptGone(endView);
                break;
            case NetWorkError:
                setExceptGone(failureView);
                break;
            default:
                break;
        }
    }

    private void setAllGone() {
        loadingView.setVisibility(View.GONE);
        failureView.setVisibility(View.GONE);
        endView.setVisibility(View.GONE);
    }

    private void setExceptGone(View view) {
        loadingView.setVisibility(view == loadingView ? View.VISIBLE : View.GONE);
        failureView.setVisibility(view == failureView ? View.VISIBLE : View.GONE);
        endView.setVisibility(view == endView ? View.VISIBLE : View.GONE);
    }
}
