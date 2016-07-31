package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.end;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizEndFragment extends Fragment implements QuizEndView {

    @Inject QuizEndPresenter mPresenter;

    @Inject
    public QuizEndFragment() {
        // Required empty public constructor
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
        mPresenter.attachView(this);
        return inflater.inflate(R.layout.fragment_quiz_end, container, false);
    }

}
