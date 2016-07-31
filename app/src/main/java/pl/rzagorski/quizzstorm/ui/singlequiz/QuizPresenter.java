package pl.rzagorski.quizzstorm.ui.singlequiz;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.data.interactor.GetQuizDetailsUseCase;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.ui.base.BasePresenter;
import pl.rzagorski.quizzstorm.utils.DefaultSubscriber;
import pl.rzagorski.quizzstorm.utils.interfaces.Strategy;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuizPresenter extends BasePresenter<QuizActivityView> {
    GetQuizDetailsUseCase mGetQuizDetailsUseCase;
    Quiz mQuiz;
    CompositeSubscription mSubscribtionList;

    @Inject
    public QuizPresenter(Quiz quiz,
                         GetQuizDetailsUseCase getQuizDetailsUseCase) {
        this.mQuiz = quiz;
        mGetQuizDetailsUseCase = getQuizDetailsUseCase;
        mSubscribtionList = new CompositeSubscription();
    }

    @Override
    public void attachView(QuizActivityView mvcView) {
        super.attachView(mvcView);
        getQuizDetails(Strategy.LOCAL_AND_API);
    }

    private void getQuizDetails(@Strategy.Caching int strategy) {
        Subscriber<Quiz> quizDetailsSubscriber = new QuizSubscriber();
        Observable<Quiz> quizObservable = mGetQuizDetailsUseCase.build(strategy, mQuiz.getId());
        mSubscribtionList.add(quizDetailsSubscriber);
        quizObservable.subscribe(quizDetailsSubscriber);
    }

    public Quiz getQuiz() {
        return mQuiz;
    }

    private void updateQuizData(Quiz quiz) {

    }

    public void saveState() {

    }

    private class QuizSubscriber extends DefaultSubscriber<Quiz> {
        @Override
        public void onCompleted() {
            mSubscribtionList.remove(this);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Quiz quiz) {
            mQuiz = quiz;
            updateQuizData(quiz);
        }
    }
}
