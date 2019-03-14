package org.pursuit.pursuitjeopardy.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.Animations;
import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;

public final class QuestionFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_KEY = "org.pursuit.pursuitjeopardy.QUESTION";
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

        questionView = view.findViewById(R.id.text_question);
        answerRadioGroup = view.findViewById(R.id.answers_radio);
        answerRadioGroup.setGravity(Gravity.CENTER);
        submitButton = view.findViewById(R.id.button_submit);
        submitButton.setOnClickListener(this);
        questionView.setText(viewModel.getQuestion(viewmodelKey));
        String[] ab = viewModel.getAnswers(viewmodelKey);

        for (int i = 0; i < ab.length; i++) {
            RadioButton radioButtonView = new RadioButton(view.getContext());
            radioButtonView.setText(ab[i]);
//this code was added to tweak design of radio buttons --START
            radioButtonView.setGravity(Gravity.CENTER);
            radioButtonView.setWidth(
                    (int) Math.floor(
                            ( (view.getRootView().getWidth()) - ( .34 * view.getRootView().getWidth() ) ) / ( ab.length )));
            radioButtonView.setAutoSizeTextTypeUniformWithConfiguration(10,16,2,1);
//--END
            answerRadioGroup.addView(radioButtonView, i);
            Log.d("radio",Integer.toString(answerRadioGroup.getChildCount()));
        }
        Animations.launchedQuestionFragmentAnimate(view);
    }

    @Override
    public void onClick(View v) {
        viewModel.getQuestionObj(viewmodelKey).setAnswered(true);
        RadioButton radioButton = answerRadioGroup.findViewById(answerRadioGroup.getCheckedRadioButtonId());
        boolean isCorrect = viewModel
                .getCorrect(viewmodelKey)
                .equals(radioButton.getText().toString());
        onFragmentInteractionListener.displayResult(isCorrect);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onFragmentInteractionListener.communicateQuestionStatus(viewModel.getQuestionObj(viewmodelKey).isAnswered(),viewmodelKey);
    }
}
