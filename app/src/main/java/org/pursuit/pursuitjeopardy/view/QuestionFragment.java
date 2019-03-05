package org.pursuit.pursuitjeopardy.view;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;

public class QuestionFragment extends Fragment {
    private static final String QUESTION_KEY = "org.pursuit.pursuitjeopardy.QUESTION";

    private QuestionViewModel viewModel;
    private TextView questionView;
    private String viewmodelKey;

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
        questionView.setText(viewModel.getQuestion(viewmodelKey));
        view.animate().alpha(1.0f).setStartDelay(1000).setDuration(1200);
    }
}
