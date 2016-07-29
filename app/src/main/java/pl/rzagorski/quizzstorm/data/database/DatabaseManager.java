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
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.model.database.QuizDao;
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
                        mDatabaseUtil.deleteAllFromTable(mSession, abstractDao);
                        mSession.clear();
                        return Observable.empty();
                    }
                });
    }

    public Observable<List<Quiz>> selectQuizList() {
        return mDatabaseUtil.selectElementsAsync(mSession, mSession.getQuizDao());
    }

    public Observable<Quiz> insertQuiz(Quiz quiz) {
        return mDatabaseUtil.insertElementAsync(mSession, mSession.getQuizDao(), quiz);
    }

    public Observable<Quiz> selectQuiz(Long id) {
        return Observable.just(QuizDao.Properties.Id.eq(id))
                .flatMap(new Func1<WhereCondition, Observable<Quiz>>() {
                    @Override
                    public Observable<Quiz> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession, mSession.getQuizDao(), idCondition);
                    }
                });
    }

    public Observable<Photo> insertPhoto(Photo photo) {
        return mDatabaseUtil.insertElementAsync(mSession, mSession.getPhotoDao(), photo);
    }

    public Observable<Photo> selectPhoto(Long id) {
        return Observable.just(PhotoDao.Properties.Id.like("%" + id + "%"))
                .flatMap(new Func1<WhereCondition, Observable<Photo>>() {
                    @Override
                    public Observable<Photo> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession, mSession.getPhotoDao(), idCondition);
                    }
                });
    }

    public Observable<Category> insertCategory(Category category) {
        return mDatabaseUtil.insertElementAsync(mSession, mSession.getCategoryDao(), category);
    }

    public Observable<Category> selectCategory(Long id) {
        return Observable.just(CategoryDao.Properties.Id.eq(id))
                .flatMap(new Func1<WhereCondition, Observable<Category>>() {
                    @Override
                    public Observable<Category> call(WhereCondition idCondition) {
                        return mDatabaseUtil.selectElementByConditionAsync(mSession, mSession.getCategoryDao(), idCondition);
                    }
                });
    }
}