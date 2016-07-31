package pl.rzagorski.quizzstorm.ui.list;

import java.util.List;

import pl.rzagorski.quizzstorm.model.ui.ListRow;
import pl.rzagorski.quizzstorm.ui.base.MvpView;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public interface QuizListView extends MvpView {
    void onItemsLoaded(List<ListRow> quizList);
    void openQuiz();
}
