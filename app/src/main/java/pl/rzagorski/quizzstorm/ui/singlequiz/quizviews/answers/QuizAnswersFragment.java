package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;

public class QuizAnswersFragment extends Fragment implements QuizAnswersView {

    @Inject QuizAnswersPresenter mPresenter;

    @BindView(R.id.quiz_progress) ProgressBar mPbQuizProgress;
    @BindView(R.id.quiz_time) TextView mTvQuizTime;
    @BindView(R.id.answers_view_pager) ViewPager mVpAnswers;

    Unbinder butterknifeUnbinder;

    @Inject
    public QuizAnswersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        QuizActivity deviationActivity = ((QuizActivity) getActivity());
        deviationActivity.getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        butterknifeUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        butterknifeUnbinder.unbind();
    }

    @Override
    public void setQuestions(List<Question> questionList) {
        mVpAnswers.setAdapter(new QuestionsAdapter(questionList));
    }

    @Override
    public void updateTime(final Long seconds) {
        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:SS", Locale.ENGLISH);
                mTvQuizTime.setText(format.format(new Date(seconds)));
            }
        });*/
    }

    @Override
    public void showCorrectAnswerView() {

    }

    @Override
    public void showIncorrectAnswerView() {

    }

    @Override
    public void showNextQuestion() {

    }
}
