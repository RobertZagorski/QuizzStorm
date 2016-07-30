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

    public <T> Observable<List<T>> selectElementsAsync(AbstractDao<T, ?> absDao) {
        return Observable.just(selectElements(absDao));
    }

    public <T> Observable<T> selectElementByConditionAsync(AbstractDao<T, ?> absDao,
                                                           WhereCondition... conditions) {
        return Observable.just(selectElementByCondition(absDao, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(AbstractDao<T, ?> absDao,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByCondition(absDao, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(AbstractDao<T, ?> absDao,
                                                                  Property sortProperty,
                                                                  String sortStrategy,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByConditionAndSort(absDao, sortProperty, sortStrategy, conditions));
    }

    public <T> Observable<List<T>> selectElementsByConditionAsync(AbstractDao<T, ?> absDao,
                                                                  Property sortProperty,
                                                                  boolean nullHandling,
                                                                  String sortStrategy,
                                                                  WhereCondition... conditions) {
        return Observable.just(selectElementsByConditionAndSortWithNullHandling(absDao, sortProperty, nullHandling, sortStrategy, conditions));
    }

    public <T> Observable<List<T>> insertElementsAsync(final AbstractDao<T, ?> absDao,
                                                       final T[] elements) {
        return Observable.create(new Observable.OnSubscribe<List<T>>() {
            @Override
            public void call(Subscriber<? super List<T>> subscriber) {
                List<T> elementsList = new ArrayList<T>();
                Collections.addAll(elementsList, elements);
                insertElements(absDao, elementsList);
                subscriber.onNext(elementsList);
                subscriber.onCompleted();
            }
        }).compose(ObservableUtils.<List<T>>applySchedulers());
    }

    public <T> Observable<List<T>> insertElementsAsync(AbstractDao<T, ?> absDao,
                                                       List<T> elementList) {
        return Observable.just(insertElements(absDao, elementList));
    }

    public <T> Observable<T> insertElementAsync(final AbstractDao<T, ?> absDao,
                                                final T element) {
        return Observable.just(insertElement(absDao, element));
    }

    public <T> Observable<T> deleteElementAsync(final DaoSession session,
                                                final AbstractDao<T, ?> absDao,
                                                final T element) {
        return Observable.just(deleteElement(session, absDao, element));

    }

    public <T> List<T> selectElements(AbstractDao<T, ?> dao) {
        if (dao == null) {
            return null;
        }
        QueryBuilder<T> qb = dao.queryBuilder();
        return qb.list();
    }

    public <T> List<T> insertElements(AbstractDao<T, ?> absDao, List<T> items) {
        if (items == null || items.size() == 0 || absDao == null) {
            return null;
        }
        absDao.insertOrReplaceInTx(items);
        return items;
    }

    public <T> T insertElement(AbstractDao<T, ?> absDao, T item) {
        if (item == null || absDao == null) {
            return null;
        }
        absDao.insertOrReplaceInTx(item);
        return item;
    }

    public <T> void updateElements(AbstractDao<T, ?> absDao, List<T> items) {
        if (items == null || items.size() == 0 || absDao == null) {
            return;
        }
        absDao.updateInTx(items);
    }

    public <T> T selectElementByCondition(AbstractDao<T, ?> absDao,
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

    public <T> List<T> selectElementsByCondition(AbstractDao<T, ?> absDao,
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

    public <T> List<T> selectElementsByConditionAndSort(AbstractDao<T, ?> absDao,
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

    public <T> List<T> selectElementsByConditionAndSortWithNullHandling(AbstractDao<T, ?> absDao,
                                                                        Property sortProperty,
                                                                        boolean handleNulls,
                                                                        String sortStrategy,
                                                                        WhereCondition... conditions) {
        if (!handleNulls) {
            return selectElementsByConditionAndSort(absDao, sortProperty, sortStrategy, conditions);
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

    public <T, V extends Class> List<T> selectByJoin(AbstractDao<T, ?> absDao,
                                                     V className,
                                                     Property property, WhereCondition whereCondition) {
        QueryBuilder<T> qb = absDao.queryBuilder();
        qb.join(className, property).where(whereCondition);
        return qb.list();
    }

    public <T> void deleteElementsByCondition(AbstractDao<T, ?> absDao,
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

    public <T> T deleteElement(DaoSession session, AbstractDao<T, ?> absDao, T object) {
        if (absDao == null) {
            return null;
        }
        absDao.delete(object);
        session.clear();
        return object;
    }

    public <T, V extends Class> void deleteByJoin(AbstractDao<T, ?> absDao,
                                                  V className,
                                                  Property property, WhereCondition whereCondition) {
        QueryBuilder<T> qb = absDao.queryBuilder();
        qb.join(className, property).where(whereCondition);
        qb.buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public <T> void deleteAllFromTable(AbstractDao<T, ?> absDao) {
        if (absDao == null) {
            return;
        }
        absDao.deleteAll();
    }

    public <T> long countElements(AbstractDao<T, ?> absDao) {
        if (absDao == null) {
            return 0;
        }
        return absDao.count();
    }
}
