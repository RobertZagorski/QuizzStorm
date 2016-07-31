package pl.rzagorski.quizzstorm.data.interactor;

import android.util.Pair;

import java.util.List;

import pl.rzagorski.quizzstorm.data.api.ApiManager;
import pl.rzagorski.quizzstorm.data.database.DatabaseManager;
import pl.rzagorski.quizzstorm.model.api.ApiQuestion;
import pl.rzagorski.quizzstorm.model.api.ApiQuizExtendedModel;
import pl.rzagorski.quizzstorm.model.api.ApiRate;
import pl.rzagorski.quizzstorm.model.api.request.GetQuizDetailsRequest;
import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.model.database.QuizRates;
import pl.rzagorski.quizzstorm.model.database.Rate;
import pl.rzagorski.quizzstorm.utils.ObservableUtils;
import pl.rzagorski.quizzstorm.utils.interfaces.Strategy;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

import static pl.rzagorski.quizzstorm.utils.ObservableUtils.pairWith;
import static pl.rzagorski.quizzstorm.utils.ObservableUtils.zipWith;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class GetQuizDetailsUseCase implements UseCase<Quiz> {
    private static final Long DEFAULT_COUNT = 0L;
    DatabaseManager mDatabaseManager;
    ApiManager mApiManager;

    public GetQuizDetailsUseCase(DatabaseManager databaseManager, ApiManager apiManager) {
        this.mDatabaseManager = databaseManager;
        this.mApiManager = apiManager;
    }

    @Override
    public Observable<Quiz> build() {
        return null;
    }

    public Observable<Quiz> build(@Strategy.Caching int strategy, Long quizId) {
        if (strategy == Strategy.LOCAL) {
            return getQuizFromDatabase(quizId);
        } else if (strategy == Strategy.API) {
            return getQuizFromApi(quizId);
        } else if (strategy == Strategy.LOCAL_AND_API) {
            return getQuizFromDatabaseAndApi(quizId);
        } else {
            return getQuizFromApi(quizId);
        }
    }

    private Observable<Quiz> getQuizFromDatabase(Long quizId) {
        return mDatabaseManager.selectQuiz(quizId)
                .compose(ObservableUtils.<Quiz>applySchedulers());
    }

    private Observable<Quiz> getQuizFromDatabaseAndApi(Long quizId) {
        return getQuizFromApi(quizId)
                .startWith(getQuizFromDatabase(quizId));
    }

    private Observable<Quiz> getQuizFromApi(Long quiId) {
        return mApiManager.getQuizDetails(new GetQuizDetailsRequest(quiId, DEFAULT_COUNT))
                .flatMap(new Func1<ApiQuizExtendedModel, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(ApiQuizExtendedModel apiQuizExtendedModel) {
                        return insertApiQuizIntoDatabase(apiQuizExtendedModel);
                    }
                })
                .compose(ObservableUtils.<Quiz>applySchedulers());
    }

    private Observable<Quiz> insertApiQuizIntoDatabase(ApiQuizExtendedModel apiQuizExtendedModel) {
        return Observable.just(apiQuizExtendedModel)
                .flatMap(new Func1<ApiQuizExtendedModel, Observable<ApiQuizExtendedModel>>() {
                    @Override
                    public Observable<ApiQuizExtendedModel> call(ApiQuizExtendedModel quiz) {
                        return zipWith(insertApiRateIntoDatabase(quiz.getRates(), quiz.getId()), quiz);
                    }
                }).flatMap(new Func1<ApiQuizExtendedModel, Observable<ApiQuizExtendedModel>>() {
                    @Override
                    public Observable<ApiQuizExtendedModel> call(ApiQuizExtendedModel quiz) {
                        return zipWith(insertAPiQuestionListIntoDatabase(quiz.getQuestions(), quiz.getId()), quiz);
                    }
                }).map(new Func1<ApiQuizExtendedModel, Quiz>() {
                    @Override
                    public Quiz call(ApiQuizExtendedModel quiz) {
                        return quiz.transform();
                    }
                }).flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return mDatabaseManager.insertQuiz(quiz);
                    }
                }).flatMap(new Func1<Quiz, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(Quiz quiz) {
                        return mDatabaseManager.selectQuiz(quiz.getId());
                    }
                });
    }

    private Observable<List<Rate>> insertApiRateIntoDatabase(List<ApiRate> apiRateList, Long quizId) {
        if (apiRateList == null || quizId == null) {
            return Observable.empty();
        }
        Observable<Long> quizIdObs = Observable.just(quizId).repeat(apiRateList.size());
        return Observable.from(apiRateList)
                .zipWith(quizIdObs, new Func2<ApiRate, Long, Pair<Rate, Long>>() {
                    @Override
                    public Pair<Rate, Long> call(ApiRate apiRate, Long quizId) {
                        Rate rate = apiRate.transform();
                        return new Pair<>(rate, quizId);
                    }
                }).flatMap(new Func1<Pair<Rate, Long>, Observable<Pair<Rate, Long>>>() {
                    @Override
                    public Observable<Pair<Rate, Long>> call(Pair<Rate, Long> rateQuizIdPair) {
                        return pairWith(mDatabaseManager.insertRate(rateQuizIdPair.first), rateQuizIdPair.second);
                    }
                }).flatMap(new Func1<Pair<Rate, Long>, Observable<Rate>>() {
                    @Override
                    public Observable<Rate> call(Pair<Rate, Long> rateQuizIdPair) {
                        QuizRates quizRates = new QuizRates();
                        quizRates.setRate(rateQuizIdPair.first.getId());
                        quizRates.setQuiz(rateQuizIdPair.second);
                        return zipWith(mDatabaseManager.insertQuizRate(quizRates), rateQuizIdPair.first);
                    }
                }).toList();
    }

    private Observable<List<Question>> insertAPiQuestionListIntoDatabase(List<ApiQuestion> apiQuestionList, Long quizId) {
        if (apiQuestionList == null || quizId == null) {
            return Observable.empty();
        }
        Observable<Long> quizIdObs = Observable.just(quizId).repeat(apiQuestionList.size());
        return Observable.from(apiQuestionList)
                .zipWith(quizIdObs, new Func2<ApiQuestion, Long, Question>() {
                    @Override
                    public Question call(ApiQuestion apiQuestion, Long quizId) {
                        Question question = apiQuestion.transform();
                        question.setQuiz(quizId);
                        return question;
                    }
                }).toList()
                .flatMap(new Func1<List<Question>, Observable<List<Question>>>() {
                    @Override
                    public Observable<List<Question>> call(List<Question> questionList) {
                        return zipWith(mDatabaseManager.deleteQuestions(questionList.get(0).getQuiz()), questionList);
                    }
                })
                .flatMap(new Func1<List<Question>, Observable<List<Question>>>() {
                    @Override
                    public Observable<List<Question>> call(List<Question> questionList) {
                        return mDatabaseManager.insertQuestions(questionList);
                    }
                });
    }
}
