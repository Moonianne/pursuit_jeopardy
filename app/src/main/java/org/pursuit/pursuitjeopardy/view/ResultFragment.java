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

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;

public final class ResultFragment extends Fragment {
    private static final String CORRECT = "org.pursuit.pursuitjeopardy.RESULT";

    private OnFragmentInteractionListener onFragmentInteractionListener;
    private boolean isCorrect;

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
        view.<TextView>findViewById(R.id.text_result)
                .setText(isCorrect ? "CORRECT!" : "WRONG!");
    }

    @Override
    public void onResume() {
        super.onResume();
        CountDownTimer counterTimer = new CountDownTimer(5000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //No-Op
            }

            @Override
            public void onFinish() {
                onFragmentInteractionListener.removeResultFragment();
            }
        };
        counterTimer.start();
    }
}