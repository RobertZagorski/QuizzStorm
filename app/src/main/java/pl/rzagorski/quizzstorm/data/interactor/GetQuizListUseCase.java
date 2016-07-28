package pl.rzagorski.quizzstorm.data.interactor;

import java.util.List;

import pl.rzagorski.quizzstorm.model.database.Quiz;
import rx.Observable;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class GetQuizListUseCase implements UseCase<List<Quiz>>{

    @Override
    public Observable<List<Quiz>> build() {
        return null;
    }
}
