package pl.rzagorski.quizzstorm.data.interactor;

import java.util.List;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.data.api.ApiManager;
import pl.rzagorski.quizzstorm.data.database.DatabaseManager;
import pl.rzagorski.quizzstorm.model.api.ApiQuiz;
import pl.rzagorski.quizzstorm.model.api.request.GetQuizListRequest;
import pl.rzagorski.quizzstorm.model.api.response.GetQuizListResponse;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.utils.ObservableUtils;
import pl.rzagorski.quizzstorm.utils.interfaces.Strategy;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class GetQuizListUseCase implements UseCase<List<Quiz>> {
    private static final Long DEFAULT_START_INDEX = 0L;
    private static final Long DEFAULT_END_INDEX = 100L;
    DatabaseManager mDatabaseManager;
    ApiManager mApiManager;

    @Inject
    public GetQuizListUseCase(DatabaseManager databaseManager, ApiManager apiManager) {
        mDatabaseManager = databaseManager;
        mApiManager = apiManager;
    }

    @Override
    public Observable<List<Quiz>> build() {
        return getQuizListFromApi();
    }

    public Observable<List<Quiz>> build(@Strategy.Caching int cachingStrategy) {
        if (cachingStrategy == Strategy.LOCAL) {
            return getQuizListFromDatabase();
        } else if (cachingStrategy == Strategy.API) {
            return getQuizListFromApi();
        } else if (cachingStrategy == Strategy.LOCAL_AND_API) {
            return getQuizListFromDatabaseAndApi();
        } else {
            return build();
        }
    }

    private Observable<List<Quiz>> getQuizListFromDatabase() {
        return mDatabaseManager.selectQuizList()
                .compose(ObservableUtils.<List<Quiz>>applySchedulers());
    }

    private Observable<List<Quiz>> getQuizListFromDatabaseAndApi() {
        return getQuizListFromApi()
                .startWith(getQuizListFromDatabase());
    }

    private Observable<List<Quiz>> getQuizListFromApi() {
        return mApiManager.getQuizList(new GetQuizListRequest(DEFAULT_START_INDEX, DEFAULT_END_INDEX))
                .map(new Func1<GetQuizListResponse, List<ApiQuiz>>() {
                    @Override
                    public List<ApiQuiz> call(GetQuizListResponse quizListResponse) {
                        return quizListResponse.getItems();
                    }
                }).flatMap(new Func1<List<ApiQuiz>, Observable<ApiQuiz>>() {
                    @Override
                    public Observable<ApiQuiz> call(List<ApiQuiz> apiQuizList) {
                        return Observable.from(apiQuizList);
                    }
                }).map(new Func1<ApiQuiz, Quiz>() {
                    @Override
                    public Quiz call(ApiQuiz apiQuiz) {
                        return apiQuiz.transform();
                    }
                }).flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return insertQuizIntoDatabase(quiz);
                    }
                }).toList().flatMap(new Func1<List<Quiz>, Observable<List<Quiz>>>() {
                    @Override
                    public Observable<List<Quiz>> call(List<Quiz> quizList) {
                        return getQuizListFromDatabase();
                    }
                }).compose(ObservableUtils.<List<Quiz>>applySchedulers());
    }

    private Observable<Quiz> insertQuizIntoDatabase(Quiz quiz) {
        return Observable.just(quiz)
                .flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return mDatabaseManager.insertQuiz(quiz);
                    }
                }).flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return ObservableUtils.zipWith(mDatabaseManager.insertPhoto(quiz.getPhotoRef()), quiz);
                    }
                }).flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return ObservableUtils.zipWith(mDatabaseManager.insertCategory(quiz.getCategoryRef()), quiz);
                    }
                });
    }
}
