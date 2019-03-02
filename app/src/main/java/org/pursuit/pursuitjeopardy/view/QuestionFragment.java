package org.pursuit.pursuitjeopardy.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;

public class QuestionFragment extends Fragment {
    private static final String QUESTION_KEY = "org.pursuit.pursuitjeopardy.QUESTION";

    private TextView questionView;
    private String question;

    public static QuestionFragment newInstance(@NonNull QuestionsModel question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_KEY, question.getQuestion());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION_KEY);
        } else {
            throw new IllegalArgumentException("Fragment Needs a Question");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionView = view.findViewById(R.id.text_question);
        questionView.setText(question);
    }
}
