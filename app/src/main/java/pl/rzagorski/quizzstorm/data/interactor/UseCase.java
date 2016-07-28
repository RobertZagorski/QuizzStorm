package pl.rzagorski.quizzstorm.data.interactor;


import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public interface UseCase<T> {

    Observable<T> build();
}
