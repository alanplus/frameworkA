package com.xm.framework.base.recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xm.framework.rx.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Mouse on 2019-06-26.
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private boolean isSlidingUpward = false;

    private boolean isLoading = false;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward = dy > 0;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();
            // 判断是否滑动到了最后一个item，并且是向上滑动
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward && !isLoading) {
                //加载更多
                if (onPreLoadMoreOnMainThread()) {
                    isLoading = true;
                    Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                            e.onNext(onLoadMoreOnThread());
                            isLoading = false;
                        }
                    }).compose(RxSchedulers.<Integer>threadMain()).doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer state) {
                            onLoadMoreFinishOnMainThread(state);
                        }
                    }).onErrorReturn(new Function<Throwable, Integer>() {
                        @Override
                        public Integer apply(Throwable throwable) {
                            onLoadMoreFinishOnMainThread(FootView.NetWorkError);
                            return 100;
                        }
                    }).subscribe();
                }
            }
        }
    }

    protected boolean onPreLoadMoreOnMainThread() {
        return true;
    }

    protected abstract int onLoadMoreOnThread() throws Exception;

    protected abstract void onLoadMoreFinishOnMainThread(int state);

    public boolean isSlidingUpward() {
        return isSlidingUpward;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setSlidingUpward(boolean slidingUpward) {
        isSlidingUpward = slidingUpward;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
