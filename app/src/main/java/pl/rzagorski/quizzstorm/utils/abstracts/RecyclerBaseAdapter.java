package pl.rzagorski.quizzstorm.utils.abstracts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.rzagorski.quizzstorm.model.ui.RecyclerViewModel;
import pl.rzagorski.quizzstorm.utils.interfaces.RecyclerViewOnItemClickListener;

/**
 * Created by Robert Zagorski on 2015-07-03.
 */
public abstract class RecyclerBaseAdapter<K extends RecyclerBaseAdapter.ViewHolder, L extends RecyclerViewModel> extends RecyclerView.Adapter<K> {
    private final Context mContext;
    /*Generic list of items*/
    private List<L> mList;

    public RecyclerBaseAdapter(Context mContext, List<L> list) {
        this.mContext = mContext;
        mList = list;
        if (mList == null) {
            mList = new ArrayList<>();
        }
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(getRowView(), parent, false);
        return createViewHolder(sView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract int getRowView();

    public abstract K createViewHolder(View rowView);

    protected Context getContext() {
        return mContext;
    }

    public L getItemAt(int position) {
        return mList.get(position);
    }

    public void addItem(L item) {
        mList.add(item);
    }

    public void addItemWithNotify(L item) {
        mList.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItemAtWithoutNotify(int position, L item) {
        mList.add(position, item);
    }

    public void addItemAt(int position, L item) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItemAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        mList.clear();
        mList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addAllItems(Collection<L> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setItemAt(int position, L item) {
        mList.set(position, item);
        notifyItemChanged(position);
    }

    public void setItemAtWithoutNotifying(int position, L item) {
        mList.set(position, item);
    }

    public boolean isEmpty() {
        if (mList == null) {
            return true;
        }
        return mList.isEmpty();
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            L item = getItemAt(getLayoutPosition());
            if (item == null) {
                return;
            }
            RecyclerViewOnItemClickListener listener = item.getRowListener();
            if (listener == null) {
                return;
            }
            listener.onItemClick(v, (L) mList.get(getLayoutPosition()), getLayoutPosition());

        }
    }
}