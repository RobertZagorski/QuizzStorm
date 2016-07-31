package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.model.database.Answer;
import pl.rzagorski.quizzstorm.model.database.Question;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuestionsAdapter extends PagerAdapter {
    List<Question> mQuestionList;

    public QuestionsAdapter(List<Question> questionList) {
        mQuestionList = questionList;
    }

    @Override
    public int getCount() {
        return mQuestionList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext())
                .inflate(R.layout.question_layout, container, true);
        initTitle(viewGroup, position);
        initAnswers(viewGroup, position);
        return container;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    private void initTitle(ViewGroup viewGroup, int position) {
        String questionTitle = mQuestionList.get(position).getText();
        if (questionTitle == null) {
            return;
        }
        TextView mTvQuestionTitle = (TextView) viewGroup.findViewById(R.id.quiz_question);
        mTvQuestionTitle.setText(questionTitle);
    }

    private void initAnswers(ViewGroup viewGroup, int position) {
        List<Answer> answerList = mQuestionList.get(position).getAnswersRef();
        RadioGroup answerRadioGroup = (RadioGroup) viewGroup.findViewById(R.id.answer_radio_group);
        for (Answer answer : answerList) {
            RadioButton answerButton = (RadioButton) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.answer_button, answerRadioGroup, false);
            answerButton.setText(answer.getText());
            answerRadioGroup.addView(answerButton);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
