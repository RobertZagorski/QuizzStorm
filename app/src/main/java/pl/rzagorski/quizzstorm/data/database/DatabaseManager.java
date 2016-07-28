package pl.rzagorski.quizzstorm.data.database;

import android.content.Context;

import javax.inject.Inject;

import de.greenrobot.dao.AbstractDao;
import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationContext;
import pl.rzagorski.quizzstorm.model.database.DaoSession;
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
}