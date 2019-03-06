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

public final class ResultFragment extends Fragment {
    private static final String CORRECT = "org.pursuit.pursuitjeopardy.RESULT";
    private boolean isCorrect;

    public static ResultFragment newInstance(boolean isCorrect) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putBoolean(CORRECT, isCorrect);
        resultFragment.setArguments(args);
        return resultFragment;
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
}