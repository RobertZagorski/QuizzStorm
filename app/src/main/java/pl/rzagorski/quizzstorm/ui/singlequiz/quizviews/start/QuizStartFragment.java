package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.start;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizStartFragment extends Fragment implements QuizStartView {

    @Inject QuizStartPresenter mPresenter;

    @BindView(R.id.quiz_title) TextView mTvTitle;
    @BindView(R.id.quiz_description) TextView mTvDescription;

    Unbinder butterknifeUnbinder;

    @Inject
    public QuizStartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        QuizActivity quizActivity = ((QuizActivity) getActivity());
        quizActivity.getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_start, container, false);
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
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    @Override
    public void setDescription(String content) {
        mTvDescription.setText(content);
    }

    @OnClick(R.id.button_start)
    public void onButtonStartClick() {
        mPresenter.onStart();
        ((QuizActivity)getActivity()).goToAnswersFragment();
    }
}
