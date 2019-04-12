package com.xm.framework.tools.timer;

import android.support.annotation.IntDef;

import com.xm.framework.tools.Logger;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Mouse on 2019/3/4.
 */
public class XmTimerTask implements Serializable {

    /**
     * 累计秒值
     */
    private int count;

    private String id;

    /**
     * 0 暂停
     * 1 开始
     * 2 停止
     */
    private int state;

    public static final int STATE_PAUSE = 0;
    public static final int STATE_START = 1;
    public static final int STATE_STOP = 2;

    @IntDef({STATE_PAUSE, STATE_START, STATE_STOP})
    @interface State {
    }

    public XmTimerTask() {
        id = "android" + UUID.randomUUID().toString().replace("-", "");
        state = STATE_PAUSE;
    }

    public int getCount() {
        return count + 1;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add() {
        this.count++;
    }

    public void add(int n) {
        this.count += n;
    }

    public String getId() {
        return id;
    }

    public void setState(@State int state) {
        this.state = state;
    }

    public void pause() {
        setState(STATE_PAUSE);
    }

    public void start() {
        setState(STATE_START);
    }

    public void stop() {
        setState(STATE_STOP);
    }

    public int getState() {
        return state;
    }
}
