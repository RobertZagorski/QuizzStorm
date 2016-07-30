package pl.rzagorski.quizzstorm.data.database;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.WhereCondition;
import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationContext;
import pl.rzagorski.quizzstorm.model.database.Category;
import pl.rzagorski.quizzstorm.model.database.CategoryDao;
import pl.rzagorski.quizzstorm.model.database.DaoSession;
import pl.rzagorski.quizzstorm.model.database.Photo;
import pl.rzagorski.quizzstorm.model.database.PhotoDao;
import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.model.database.QuestionDao;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.model.database.QuizDao;
import pl.rzagorski.quizzstorm.model.database.QuizRates;
import pl.rzagorski.quizzstorm.model.database.QuizRatesDao;
import pl.rzagorski.quizzstorm.model.database.Rate;
import pl.rzagorski.quizzstorm.model.database.RateDao;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Robert Zagórski on 2016-07-21.
 */
public class DatabaseManager {
    private Context mContext;
    private DaoSession mSession;
    private DatabaseUtil mDatabaseUtil;

    @Inject
    public DatabaseManager(@ApplicationContext Context context, DatabaseUtil databaseUtil) {
        mContext = context;
        mDatabaseUtil = databaseUtil;
        mSession = mDatabaseUtil.initHelper(context);
    }

    public <T> Observable clearTable(AbstractDao<T, ?> dao) {
        return Observable.just(dao)
                .flatMap(new Func1<AbstractDao<T, ?>, Observable<?>>() {
                    @Override
                    public Observable<?> call(AbstractDao<T, ?> abstractDao) {
                        mDatabaseUtil.deleteAllFromTable(abstractDao);
                        mSession.clear();
                        return Observable.empty();
                    }
                });
    }

    public Observable<List<Quiz>> selectQuizList() {
        return mDatabaseUtil.selectElementsAsync(mSession.getQuizDao());
    }

    public Observable<Quiz> insertQuiz(Quiz quiz) {
        return mDatabaseUtil.insertElementAsync(mSession.getQuizDao(), quiz);
    }

    public Observable<Quiz> selectQuiz(Long id) {
        return Observable.just(QuizDao.Properties.Id.eq(id))
                .flatMap(new Func1<WhereCondition, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession.getQuizDao(), idCondition);
                    }
                });
    }

    public Observable<Photo> insertPhoto(Photo photo) {
        return mDatabaseUtil.insertElementAsync(mSession.getPhotoDao(), photo);
    }

    public Observable<Photo> selectPhoto(Long id) {
        return Observable.just(PhotoDao.Properties.Id.like("%" + id + "%"))
                .flatMap(new Func1<WhereCondition, Observable<Photo>>() {
                    @Override
                    public Observable<Photo> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession.getPhotoDao(), idCondition);
                    }
                });
    }

    public Observable<Category> insertCategory(Category category) {
        return mDatabaseUtil.insertElementAsync(mSession.getCategoryDao(), category);
    }

    public Observable<Category> selectCategory(Long id) {
        return Observable.just(CategoryDao.Properties.Id.eq(id))
                .flatMap(new Func1<WhereCondition, Observable<Category>>() {
                    @Override
                    public Observable<Category> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession.getCategoryDao(), idCondition);
                    }
                });
    }

    public Observable<Rate> insertRate(Rate rate) {
        Observable<Rate> getNewestRateObs = Observable.just(mSession.getRateDao().count())
                .map(new Func1<Long, WhereCondition>() {
                    @Override
                    public WhereCondition call(Long newestID) {
                        return RateDao.Properties.Id.eq(newestID);
                    }
                }).flatMap(new Func1<WhereCondition, Observable<Rate>>() {
                    @Override
                    public Observable<Rate> call(WhereCondition newestIdCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession.getRateDao(), newestIdCondition);
                    }
                });
        return mDatabaseUtil.insertElementAsync(mSession.getRateDao(), rate)
                .concatWith(getNewestRateObs);
    }

    public Observable<Long> deleteQuizRates(Long quizId) {
        return Observable.just(quizId)
                .flatMap(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long quizId) {
                        mDatabaseUtil.deleteByJoin(
                                mSession.getQuizRatesDao(),
                                QuizRates.class,
                                QuizRatesDao.Properties.Quiz,
                                QuizRatesDao.Properties.Quiz.eq(quizId));
                        return Observable.just(quizId);
                    }
                });
    }

    public Observable<QuizRates> insertQuizRate(QuizRates quizRates) {
        return mDatabaseUtil.insertElementAsync(mSession.getQuizRatesDao(), quizRates);
    }

    public Observable<Question> insertQuestion(Question question) {
        return mDatabaseUtil.insertElementAsync(mSession.getQuestionDao(), question);
    }

    public Observable<List<Question>> insertQuestions(List<Question> questionList) {
        return mDatabaseUtil.insertElementsAsync(mSession.getQuestionDao(), questionList);
    }

    public Observable<Long> deleteQuestions(Long quizId) {
        return Observable.just(quizId)
                .flatMap(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long quizId) {
                        WhereCondition idCondition = QuestionDao.Properties.Quiz.eq(quizId);
                        mDatabaseUtil.deleteElementsByCondition(mSession.getQuestionDao(), idCondition);
                        return Observable.just(quizId);
                    }
                });
    }

    public Observable<List<Question>> selectQuestions(Long quizId) {
        return Observable.just(QuestionDao.Properties.Quiz.eq(quizId))
                .flatMap(new Func1<WhereCondition, Observable<List<Question>>>() {
                    @Override
                    public Observable<List<Question>> call(WhereCondition quizIdCondition) {
                        return mDatabaseUtil.selectElementsByConditionAsync(mSession.getQuestionDao(), quizIdCondition);
                    }
                });
    }
}