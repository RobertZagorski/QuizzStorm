package pl.rzagorski.quizzstorm.ui.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.data.ScopeManager;
import pl.rzagorski.quizzstorm.data.interactor.GetQuizListUseCase;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.model.ui.ListRow;
import pl.rzagorski.quizzstorm.ui.base.BasePresenter;
import pl.rzagorski.quizzstorm.utils.DefaultSubscriber;
import pl.rzagorski.quizzstorm.utils.interfaces.Strategy;
import rx.Observable;
import rx.Subscriber;
import rx.internal.util.SubscriptionList;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ListPresenter extends BasePresenter<QuizListView> {
    GetQuizListUseCase mGetQuizListUseCase;
    ScopeManager mScopeManager;
    SubscriptionList subscriptionList;

    private List<Quiz> mQuizList;

    @Inject
    public ListPresenter(GetQuizListUseCase getQuizListUseCase,
                         ScopeManager scopeManager) {
        this.mGetQuizListUseCase = getQuizListUseCase;
        this.mScopeManager = scopeManager;
        subscriptionList = new SubscriptionList();
    }

    @Override
    public void attachView(QuizListView mvcView) {
        super.attachView(mvcView);
        getQuizList();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscriptionList != null && !subscriptionList.isUnsubscribed()) {
            subscriptionList.clear();
        }
    }

    public void getQuizList() {
        Subscriber<List<Quiz>> listSubscriber = new ListSubscriber();
        if (mQuizList != null) {
            listSubscriber.onNext(mQuizList);
            return;
        }
        Observable<List<Quiz>> listObservable = mGetQuizListUseCase.build(Strategy.LOCAL_AND_API);
        subscriptionList.add(listSubscriber);
        listObservable.subscribe(listSubscriber);
    }

    private List<ListRow> createList(List<Quiz> quizList) {
        List<ListRow> rowList = new ArrayList<>(quizList.size());
        for (Quiz quiz : quizList) {
            ListRow row = new ListRow();
            row.setId(quiz.getId());
            row.setTitle(quiz.getTitle());
            row.setQuestionCount(quiz.getQuestions());
            if (quiz.getPhotoRef() != null) {
                row.setImageUrl(quiz.getPhotoRef().getUrl());
            }
            row.setCategoryName(quiz.getCategoryRef().getNameEng());
            rowList.add(row);
        }
        return rowList;
    }

    public void onQuizChosen(Long quizId, int position) {
        Quiz quiz = mQuizList.get(position);
        if (!quiz.getId().equals(quizId)) {

        }
        mScopeManager.createQuizComponent(quiz);
        getView().openQuiz();
    }

    private class ListSubscriber extends DefaultSubscriber<List<Quiz>> {
        @Override
        public void onCompleted() {
            subscriptionList.remove(this);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(List<Quiz> quizList) {
            if (quizList == null) {
                return;
            }
            if (quizList.isEmpty()) {
                return;
            }
            mQuizList = quizList;
            List<ListRow> rowList = createList(quizList);
            getView().onItemsLoaded(rowList);
        }
    }
}
