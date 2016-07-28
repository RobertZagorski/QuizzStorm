package pl.rzagorski.quizzstorm.utils.interfaces;

import android.support.annotation.IntDef;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public interface Strategy {
    public static final int LOCAL = 0;
    public static final int API = 1;
    public static final int LOCAL_AND_API = 2;

    @IntDef({
            LOCAL,
            API,
            LOCAL_AND_API
    })
    public @interface Caching {
    }
}
