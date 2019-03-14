package org.pursuit.pursuitjeopardy.view;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.Animations;
import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;

public final class ResultFragment extends Fragment {
    private static final String CORRECT = "org.pursuit.pursuitjeopardy.RESULT";

    private OnFragmentInteractionListener onFragmentInteractionListener;
    private boolean isCorrect;
    private TextView resultText;

    public static ResultFragment newInstance(boolean isCorrect) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putBoolean(CORRECT, isCorrect);
        resultFragment.setArguments(args);
        return resultFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException("Host Activity Must Implement OnFragmentInteractionListener.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isCorrect = getArguments().getBoolean(CORRECT);
        } else {
            throw new RuntimeException("Result not received.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultText = view.findViewById(R.id.text_result);
        view.<TextView>findViewById(R.id.text_result)
                .setText(isCorrect ? "CORRECT!" : "WRONG!");
    }

    @Override
    public void onResume() {
        super.onResume();
        CountDownTimer counterTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                onFragmentInteractionListener.removeResultFragment();
            }
        };

        counterTimer.start();
        Animations.closeResultFragmentAnimate(resultText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(QuestionViewModel.wereAllQuestionsAnswered()){
            onFragmentInteractionListener.reportGameStatus(true);
        }
    }
}