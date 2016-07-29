package pl.rzagorski.quizzstorm.utils.interfaces;

import android.view.View;

/**
 * Created by Robert Zagorski on 2015-07-27.
 */
public interface RecyclerViewOnItemClickListener<L> {
    void onItemClick(View view, L model, int position);
}
