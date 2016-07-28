package pl.rzagorski.quizzstorm.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import pl.rzagorski.quizzstorm.model.database.DaoMaster;
import pl.rzagorski.quizzstorm.model.database.DaoSession;
import pl.rzagorski.quizzstorm.utils.ObservableUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func3;

/**
 * Created by Robert Zagorski on 14.08.15.
 */
public class DatabaseUtil {
    public static final String ORDER_ASCENDING = "ASC";
    public static final String ORDER_DESCENDING = "DESC";

    @StringDef({ORDER_ASCENDING, ORDER_DESCENDING})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Order {
    }

    private static final String DATABASE_URL = "quizstorm-db";

    @Inject
    public DatabaseUtil() {
    }

    /**
     * Initializes database session
     *
     * @param context
     */
    public DaoSession initHelper(Context context) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_URL, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();
        return session;
    }

    public <T> Observable<List<T>> selectElementsAsync(DaoSession session, AbstractDao<T, ?> absDao) {
        return Observable.just(selectElements(session, absDao));
    }

    public <T> Observable<T> selectElementByConditionAsync(DaoSession session,
                                                           AbstractDao<T, ?> absDao,
                                                           WhereCondition... conditions) {
        return Observable.just(selectElementByCondition(session, absDao, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(DaoSession session,
                                                                  AbstractDao<T, ?> absDao,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByCondition(session, absDao, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(DaoSession session,
                                                                  AbstractDao<T, ?> absDao,
                                                                  Property sortProperty,
                                                                  String sortStrategy,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByConditionAndSort(session, absDao, sortProperty, sortStrategy, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(DaoSession session,
                                                                  AbstractDao<T, ?> absDao,
                                                                  Property sortProperty,
                                                                  boolean nullHandling,
                                                                  String sortStrategy,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByConditionAndSortWithNullHandling(session, absDao, sortProperty, nullHandling, sortStrategy, conditions));
    }

    public <T> Observable<List<T>> insertElementsAsync(final DaoSession session,
                                                       final AbstractDao<T, ?> absDao,
                                                       final T[] elements) {
        return Observable.create(new Observable.OnSubscribe<List<T>>() {
            @Override
            public void call(Subscriber<? super List<T>> subscriber) {
                List<T> elementsList = new ArrayList<T>();
                Collections.addAll(elementsList, elements);
                insertElements(session, absDao, elementsList);
                subscriber.onNext(elementsList);
                subscriber.onCompleted();
            }
        }).compose(ObservableUtils.<List<T>>applySchedulers());
    }

    public <T> Observable<T> insertElementAsync(final DaoSession session,
                                                final AbstractDao<T, ?> absDao,
                                                final T element) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                insertElement(session, absDao, element);
                subscriber.onNext(element);
                subscriber.onCompleted();
            }
        }).compose(ObservableUtils.<T>applySchedulers());
    }

    public <T> Observable<T> deleteElementAsync(final DaoSession session,
                                                final AbstractDao<T, ?> absDao,
                                                final T element) {
        Observable<DaoSession> sessionObs = Observable.just(session);
        Observable<? extends AbstractDao<T, ?>> classObs = Observable.just(absDao);
        Observable<T> elementsObs = Observable.just(element);

        return Observable.zip(sessionObs, classObs, elementsObs, new Func3<DaoSession, AbstractDao<T, ?>, T, T>() {
            @Override
            public T call(DaoSession session, AbstractDao<T, ?> dao, T t) {
                deleteElement(session, dao, t);
                return t;
            }
        });
    }

    public <T> List<T> selectElements(DaoSession session, AbstractDao<T, ?> dao) {
        if (dao == null) {
            return null;
        }
        QueryBuilder<T> qb = dao.queryBuilder();
        return qb.list();
    }

    public <T> void insertElements(DaoSession session, AbstractDao<T, ?> absDao, List<T> items) {
        if (items == null || items.size() == 0 || absDao == null) {
            return;
        }
        absDao.insertOrReplaceInTx(items);
    }

    public <T> void insertElement(DaoSession session, AbstractDao<T, ?> absDao, T item) {
        if (item == null || absDao == null) {
            return;
        }
        absDao.insertOrReplaceInTx(item);
    }

    public <T> void updateElements(DaoSession session, AbstractDao<T, ?> absDao, List<T> items) {
        if (items == null || items.size() == 0 || absDao == null) {
            return;
        }
        absDao.updateInTx(items);
    }

    public <T> T selectElementByCondition(DaoSession session,
                                          AbstractDao<T, ?> absDao,
                                          WhereCondition... conditions) {
        if (absDao == null) {
            return null;
        }
        QueryBuilder<T> qb = absDao.queryBuilder();
        for (WhereCondition condition : conditions) {
            qb = qb.where(condition);
        }
        List<T> items = qb.list();
        return items != null && items.size() > 0 ? items.get(0) : null;
    }

    public <T> List<T> selectElementsByCondition(DaoSession session,
                                                 AbstractDao<T, ?> absDao,
                                                 WhereCondition... conditions) {
        if (absDao == null) {
            return null;
        }
        QueryBuilder<T> qb = absDao.queryBuilder();
        for (WhereCondition condition : conditions) {
            qb = qb.where(condition);
        }
        List<T> items = qb.list();
        return items != null ? items : null;
    }

    public <T> List<T> selectElementsByConditionAndSort(DaoSession session,
                                                        AbstractDao<T, ?> absDao,
                                                        Property sortProperty,
                                                        String sortStrategy,
                                                        WhereCondition... conditions) {
        if (absDao == null) {
            return null;
        }
        QueryBuilder<T> qb = absDao.queryBuilder();
        for (WhereCondition condition : conditions) {
            qb = qb.where(condition);
        }
        qb.orderCustom(sortProperty, sortStrategy);
        List<T> items = qb.list();
        return items != null ? items : null;
    }

    public <T> List<T> selectElementsByConditionAndSortWithNullHandling(DaoSession session,
                                                                        AbstractDao<T, ?> absDao,
                                                                        Property sortProperty,
                                                                        boolean handleNulls,
                                                                        String sortStrategy,
                                                                        WhereCondition... conditions) {
        if (!handleNulls) {
            return selectElementsByConditionAndSort(session, absDao, sortProperty, sortStrategy, conditions);
        }
        if (absDao == null) {
            return null;
        }
        QueryBuilder<T> qb = absDao.queryBuilder();
        for (WhereCondition condition : conditions) {
            qb = qb.where(condition);
        }
        qb.orderRaw("(CASE WHEN " + "T." + sortProperty.columnName + " IS NULL then 1 ELSE 0 END)," + "T." + sortProperty.columnName + " " + sortStrategy);
        List<T> items = qb.list();
        return items != null ? items : null;
    }

    public <T> void deleteElementsByCondition(DaoSession session,
                                              AbstractDao<T, ?> absDao,
                                              WhereCondition... conditions) {
        if (absDao == null) {
            return;
        }
        QueryBuilder<T> qb = absDao.queryBuilder();
        for (WhereCondition condition : conditions) {
            qb = qb.where(condition);
        }
        List<T> list = qb.list();
        absDao.deleteInTx(list);
    }

    public <T> void deleteElement(DaoSession session, AbstractDao<T, ?> absDao, T object) {
        if (absDao == null) {
            return;
        }
        absDao.delete(object);
        session.clear();
    }

    public <T> void deleteAllFromTable(DaoSession session, AbstractDao<T, ?> absDao) {
        if (absDao == null) {
            return;
        }
        absDao.deleteAll();
    }

    public <T> long countElements(DaoSession session, AbstractDao<T, ?> absDao) {
        if (absDao == null) {
            return 0;
        }
        return absDao.count();
    }
}
