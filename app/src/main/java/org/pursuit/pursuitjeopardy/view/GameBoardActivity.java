package org.pursuit.pursuitjeopardy.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;

import java.util.ArrayList;
import java.util.List;

public class GameBoardActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel viewModel;
    private List<LinearLayout> layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        findAndLoadLayout();
        setViewModel();
    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<List<QuestionsModel>>>() {
            @Override
            public void onChanged(@Nullable List<List<QuestionsModel>> lists) {
                assert lists != null;
                if (lists.size() == 5) {
                    for (int i = 0; i < lists.size(); i++) {
                        BoardInflater boardInflater = new BoardInflater(layoutList.get(i), lists.get(i));
                        boardInflater.populateLayout();
                        boardInflater.setOnTileSelectedListener(new BoardInflater.OnTileClickedListener() {
                            @Override
                            public void onTileClicked(View view) {
                                QuestionsModel questionsModel = (QuestionsModel) view.getTag();
                                //Toast.makeText(GameBoardActivity.this, questionsModel.getQuestion(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }

    private void findAndLoadLayout() {
        LinearLayout category1 = findViewById(R.id.category1);
        LinearLayout category2 = findViewById(R.id.category2);
        LinearLayout category3 = findViewById(R.id.category3);
        LinearLayout category4 = findViewById(R.id.category4);
        LinearLayout category5 = findViewById(R.id.category5);
        layoutList = new ArrayList<>();
        layoutList.add(category1);
        layoutList.add(category2);
        layoutList.add(category3);
        layoutList.add(category4);
        layoutList.add(category5);
    }

    @Override
    public void startBoard() {
    }

    @Override
    public void displayQuestion(QuestionsModel question) {
        inflateFragment(QuestionFragment.newInstance(question), true);
    }

    private void inflateFragment(Fragment fragment) {
        inflateFragment(fragment, false);
    }

    private void inflateFragment(Fragment fragment, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
