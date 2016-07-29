package pl.rzagorski.quizzstorm.ui.list;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.model.ui.BlendColor;
import pl.rzagorski.quizzstorm.model.ui.ListRow;
import pl.rzagorski.quizzstorm.utils.ResourceUtils;
import pl.rzagorski.quizzstorm.utils.abstracts.RecyclerBaseAdapter;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ListRecyclerAdapter extends RecyclerBaseAdapter<ListRecyclerAdapter.ListViewHolder, ListRow> {
    List<BlendColor> blendColorList;

    public ListRecyclerAdapter(Context mContext, List<ListRow> list) {
        super(mContext, list);
        blendColorList = new ResourceUtils().getBlendColors(mContext);
    }

    @Override
    public int getRowView() {
        return R.layout.list_row_layout;
    }

    @Override
    public ListViewHolder createViewHolder(View rowView) {
        return new ListViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListRow model = getItemAt(position);
        if (model == null) {
            return;
        }
        setTitle(holder, model);
        setQuestionCount(holder, model);
        setImage(holder, model);
        setCategory(holder, model);
        setBackground(holder, model);
    }

    private void setTitle(ListViewHolder holder, ListRow model) {
        holder.title.setText(model.getTitle());
    }

    private void setQuestionCount(ListViewHolder holder, ListRow model) {
        holder.questionCount.setText(String.valueOf(model.getQuestionCount()));
    }

    private void setImage(ListViewHolder holder, ListRow model) {

    }

    private void setCategory(ListViewHolder holder, ListRow model) {
        holder.category.setText(model.getCategoryName());
    }

    private void setBackground(ListViewHolder holder, ListRow model) {
        StateListDrawable drawable = (StateListDrawable) holder.itemLayout.getBackground();
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed},
                new ColorDrawable(ContextCompat.getColor(getContext(), R.color.material_grey_light)));
        states.addState(new int[]{},
                new ColorDrawable(blendColorList.get((int) Math.round((Math.random() * 5))).getColor()));
        holder.itemLayout.setBackground(states);

    }

    @Override
    public void onViewAttachedToWindow(ListViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ListRow model = getItemAt(holder.getAdapterPosition());
        Picasso.with(getContext()).load(model.getImageUrl()).fit().centerCrop().into(holder.image);
    }

    public class ListViewHolder extends RecyclerBaseAdapter.ViewHolder {
        CardView cardView;
        View itemLayout;
        TextView title;
        TextView questionCount;
        ImageView image;
        TextView category;

        public ListViewHolder(View view) {
            super(view);
            itemLayout = view.findViewById(R.id.row_layout);
            cardView = (CardView) view.findViewById(R.id.quiz_card_view);
            title = (TextView) view.findViewById(R.id.quiz_title);
            questionCount = (TextView) view.findViewById(R.id.quiz_question_count);
            image = (ImageView) view.findViewById(R.id.quiz_image);
            category = (TextView) view.findViewById(R.id.quiz_category);
        }
    }
}
