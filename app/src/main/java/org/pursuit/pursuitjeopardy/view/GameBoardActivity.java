package org.pursuit.pursuitjeopardy.view;


import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.viewModel.PlayerViewModel;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;


import java.util.ArrayList;
import java.util.List;


public final class GameBoardActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private QuestionViewModel questionViewModel;
    private PlayerViewModel playerViewModel;
    private List<LinearLayout> layoutList;
    private Drawable[] drawables;
    private TextView playerName;
    private TextView playerPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        setClipChildren();
        findAndLoadLayout();
        setQuestionViewModel();
        setPlayerModel();
        setDrawables();
    }

    private void findAndLoadLayout() {
        playerName = findViewById(R.id.name_text_view);
        playerPoints = findViewById(R.id.points_text_view);
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

    private void setClipChildren() {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setClipChildren(false);
    }

    private void setQuestionViewModel() {
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getListLiveData().observe(this, lists -> {
            assert lists != null;
            if (lists.size() == 5) {
                for (int i = 0; i < lists.size(); i++) {
                    BoardInflater boardInflater = new BoardInflater(layoutList.get(i), lists.get(i), drawables);
                    boardInflater.populateLayout();
                    boardInflater.setOnTileSelectedListener(view -> {
                        String questionKey = (String) view.getTag();
                        displayQuestion(questionKey);
                    });

                }
            }
        });
    }

    private void setPlayerModel() {
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playerViewModel.getPlayerPoints().observe(this, integer -> playerPoints.setText("Points: " + integer));
    }

    private void setDrawables() {
        drawables = new Drawable[]{getResources().getDrawable(R.drawable.n200),
                getResources().getDrawable(R.drawable.n400),
                getResources().getDrawable(R.drawable.n600)};
    }

    @Override
    public void displayQuestion(String key) {
        questionViewModel.setCurrentQuestionKey(key);
        inflateFragment(QuestionFragment.newInstance(key), true);
    }

    @Override
    public void displayResult(boolean isCorrect) {
        inflateFragment(ResultFragment.newInstance(isCorrect));
        playerViewModel.updateToPlayerScore(questionViewModel.pointsAllocator(isCorrect));
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
