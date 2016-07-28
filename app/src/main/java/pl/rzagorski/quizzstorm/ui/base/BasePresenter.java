package pl.rzagorski.quizzstorm.ui.base;

import android.support.annotation.CallSuper;

import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    private CompositeSubscription subscription;

    public BasePresenter() {
        subscription = new CompositeSubscription();
    }

    @CallSuper
    @Override
    public void attachView(T mvcView) {
        mMvpView = mvcView;
    }

    @CallSuper
    @Override
    public void detachView() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.clear();
        }
        //mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getView() {
        return mMvpView;
    }

    public CompositeSubscription getSubscription() {
        return subscription;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Controller.attachView(MvcView) before" +
                    " requesting data to the Controller");
        }
    }
}

