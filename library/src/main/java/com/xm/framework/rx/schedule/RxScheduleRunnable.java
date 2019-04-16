package com.xm.framework.rx.schedule;

/**
 * Created by Mouse on 2019/4/16.
 */
public abstract class RxScheduleRunnable<T> {

    public abstract T scheduleOnThread();

    public void scheduleOnMainThread(T t) {

    }

    public void onCompleOnMainThread(){

    }

    public abstract T onError(Throwable throwable);
}
