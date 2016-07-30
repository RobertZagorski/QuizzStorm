package pl.rzagorski.quizzstorm.utils;

import android.util.Pair;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Robert Zagórski on 2016-02-04.
 */
public class ObservableUtils {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> applyIOSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> applyInputScheduler() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> applyOutputScheduler() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T extends Observable<V>, V, Z> Observable<Z> zipWith(T element1Obs, Z element2) {
        return element1Obs.zipWith(Observable.just(element2), new Func2<V, Z, Z>() {
            @Override
            public Z call(V v, Z z) {
                return z;
            }
        });
    }

    public static <T extends Observable<V>, V, Z> Observable<Pair<V, Z>> pairWith(T element1Obs, Z element2) {
        return element1Obs.zipWith(Observable.just(element2), new Func2<V, Z, Pair<V, Z>>() {
            @Override
            public Pair<V, Z> call(V v, Z z) {
                return new Pair<V, Z>(v, z);
            }
        });
    }

    public static <V, Z> Pair<V, Z> pairWith(V element1, Z element2) {
        return new Pair<V, Z>(element1, element2);
    }
}