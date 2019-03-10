package org.pursuit.pursuitjeopardy.view;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;

public final class QuestionFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_KEY = "org.pursuit.pursuitjeopardy.QUESTION";
    public static final String QUESTION_STATUS_KEY = "org.pursuit.pursuitjeopardy.QUESTION_STATUS";
    public static final String QUESTION_STATUS_VIEWFINDER = "org.pursuit.pursuitjeopardy.QUESTION_STATUS_VIEWFINDER";
    private boolean questionWasAnswered;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private QuestionViewModel viewModel;
    private RadioGroup answerRadioGroup;
    private TextView questionView;
    private Button submitButton;
    private String viewmodelKey;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFragmentInteractionListener = (OnFragmentInteractionListener) context;
    }

    public static QuestionFragment newInstance(@NonNull String key) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_KEY, key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            viewmodelKey = getArguments().getString(QUESTION_KEY);
            viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
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
        view.setAlpha(0);
        view.setElevation(999999999);
        questionView = view.findViewById(R.id.text_question);
        answerRadioGroup = view.findViewById(R.id.answers_radio);
        submitButton = view.findViewById(R.id.button_submit);
        submitButton.setOnClickListener(this);
        questionView.setText(viewModel.getQuestion(viewmodelKey));
        questionWasAnswered = false;
        String[] ab = viewModel.getAnswers(viewmodelKey);
        for (int i = 0; i < ab.length; i++) {
            RadioButton radioButtonView = new RadioButton(view.getContext());
            radioButtonView.setText(ab[i]);
            answerRadioGroup.addView(radioButtonView, i);
        }
        view.animate().alpha(1.0f).setStartDelay(1000).setDuration(1200);
    }

    @Override
    public void onClick(View v) {
        questionWasAnswered = true;
        RadioButton radioButton = answerRadioGroup.findViewById(answerRadioGroup.getCheckedRadioButtonId());
        boolean isCorrect = viewModel
                .getCorrect(viewmodelKey)
                .equals(radioButton.getText().toString());
        onFragmentInteractionListener.displayResult(isCorrect);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onFragmentInteractionListener.communicateQuestionStatus(questionWasAnswered,viewmodelKey);

    }
}
