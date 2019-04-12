package com.xm.framework.tools.timer;

/**
 * Created by Mouse on 2019/4/8.
 */
public class XmDownCounterTask extends XmTimerTask {

    private OnTimeLister onTimeLister;
    private int totalTime;

    public XmDownCounterTask(OnTimeLister onTimeLister, int totalTime) {
        super();
        this.onTimeLister = onTimeLister;
        this.totalTime = totalTime;
    }

    @Override
    public void setCount(int count) {
        super.setCount(count);
        if (count >= totalTime && onTimeLister != null) {
            onTimeLister.onTimerFinishListener(getId());
        }
    }
}
