package pl.rzagorski.quizzstorm.utils;

import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * Created by Robert Zagórski on 2016-02-09.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    public void onError(Throwable e, Class<? extends DefaultSubscriber> className) {
        Logger.e(e, className.getSimpleName());
    }

    @Override
    public void onNext(T t) {
    }
}
