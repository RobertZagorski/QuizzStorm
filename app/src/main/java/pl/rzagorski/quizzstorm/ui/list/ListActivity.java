package pl.rzagorski.quizzstorm.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.QuizzstormApplication;
import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.dependencyinjection.list.ListActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.ListActivityModule;
import pl.rzagorski.quizzstorm.model.ui.ListRow;
import pl.rzagorski.quizzstorm.ui.menu.MenuActivity;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;
import pl.rzagorski.quizzstorm.utils.abstracts.RecyclerBaseAdapter;
import pl.rzagorski.quizzstorm.utils.interfaces.ComponentCreator;
import pl.rzagorski.quizzstorm.utils.interfaces.RecyclerViewOnItemClickListener;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ListActivity extends MenuActivity implements ComponentCreator<ListActivityComponent>, QuizListView {
    ListActivityComponent component;
    RecyclerView recyclerView;

    @Inject ListPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        initView();
        mPresenter.attachView(this);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_list;
    }

    @Override
    public ListActivityComponent getComponent() {
        if (component == null) {
            QuizzstormApplication application = ((QuizzstormApplication) getApplicationContext());
            component = application
                    .getApplicationComponent()
                    .provide(new ListActivityModule(this));
            application.setListComponent(component);
        }
        return component;
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.quiz_recycler_view);
        RecyclerView.Adapter adapter = new ListRecyclerAdapter(this, new ArrayList<ListRow>());
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.grid_span)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemsLoaded(List<ListRow> quizList) {
        for (ListRow row : quizList) {
            row.setRowListener(new RecyclerViewOnItemClickListener<ListRow>() {
                @Override
                public void onItemClick(View view, ListRow row, int position) {
                    onRowClick(row, position);
                }
            });
        }
        ((RecyclerBaseAdapter) recyclerView.getAdapter()).addAllItems(quizList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void onRowClick(ListRow row, int position) {
        mPresenter.onQuizChosen(row.getId(), position);
    }

    @Override
    public void openQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
