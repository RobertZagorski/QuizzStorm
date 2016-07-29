package pl.rzagorski.quizzstorm.model.ui;


import pl.rzagorski.quizzstorm.utils.interfaces.RecyclerViewOnItemClickListener;

/**
 * Created by Robert Zagorski on 2015-07-27.
 */
public class RecyclerViewModel<L> {
    private RecyclerViewOnItemClickListener<L> mListener;
    public RecyclerViewOnItemClickListener getRowListener() {
        return mListener;
    }

    public void setRowListener(RecyclerViewOnItemClickListener listener) {
        this.mListener = listener;
    }
}
