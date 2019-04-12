package com.xm.framework.tools.timer;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Mouse on 2019/3/1.
 */
public class TimerCounter {

    private HashMap<String, XmTimerTask> map = new HashMap<>();

    private static TimerCounter timerHelper;

    private TimerCounter() {
        AndroidSchedulers.mainThread().scheduleDirect(runnable, 1, TimeUnit.SECONDS);
    }

    public static TimerCounter getInstance() {
        if (null == timerHelper) {
            timerHelper = new TimerCounter();
        }
        return timerHelper;
    }

    public String createTimerTask() {
        XmTimerTask yxTimerTask = new XmTimerTask();
        map.put(yxTimerTask.getId(), yxTimerTask);
        return yxTimerTask.getId();
    }

    public String createAndStartTask() {
        XmTimerTask yxTimerTask = new XmTimerTask();
        map.put(yxTimerTask.getId(), yxTimerTask);
        yxTimerTask.start();
        return yxTimerTask.getId();
    }


    public String createDownCounterTask(OnTimeLister onTimeLister, int totalTime) {
        XmDownCounterTask downCounterTask = new XmDownCounterTask(onTimeLister, totalTime);
        map.put(downCounterTask.getId(), downCounterTask);
        return downCounterTask.getId();
    }


    public void start(String id) {
        XmTimerTask yxTimerTask = map.get(id);
        if (null != yxTimerTask) {
            yxTimerTask.start();
        }
    }

    public void pause(String id) {
        XmTimerTask yxTimerTask = map.get(id);
        if (null != yxTimerTask) {
            yxTimerTask.pause();
        }
    }

    public int stop(String id) {
        XmTimerTask yxTimerTask = map.get(id);
        if (null != yxTimerTask) {
            yxTimerTask.stop();
            map.remove(id);
            return yxTimerTask.getCount();
        }
        return 0;
    }

    public int getCount(String id) {
        XmTimerTask yxTimerTask = map.get(id);
        if (null == yxTimerTask) {
            return 1;
        }
        return yxTimerTask.getCount();
    }

    public int getCountAndDestroy(String id) {
        if (map.containsKey(id)) {
            XmTimerTask remove = map.remove(id);
            return remove.getCount();
        }
        return 1;
    }

    public void start() {
        for (String key : map.keySet()) {
            XmTimerTask yxTimerTask = map.get(key);
            yxTimerTask.start();
        }
    }

    public void pause() {
        for (String key : map.keySet()) {
            XmTimerTask yxTimerTask = map.get(key);
            yxTimerTask.pause();
        }
    }

    public void stop() {
        for (String key : map.keySet()) {
            XmTimerTask yxTimerTask = map.get(key);
            if (null != yxTimerTask) {
                yxTimerTask.stop();
            }
        }
    }

    public void clear() {
        map.clear();
        timerHelper = null;
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (map.size() == 0) {
                timerHelper = null;
                return;
            }
            for (String key : map.keySet()) {
                XmTimerTask yxTimerTask = map.get(key);
                if (null != yxTimerTask && yxTimerTask.getState() == XmTimerTask.STATE_START) {
                    yxTimerTask.add();
                }
            }
            AndroidSchedulers.mainThread().scheduleDirect(runnable, 1, TimeUnit.SECONDS);
        }
    };
}
