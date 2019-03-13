package org.pursuit.pursuitjeopardy.view;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.Animations;
import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.model.Question;
import org.pursuit.pursuitjeopardy.viewModel.PlayerViewModel;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

public final class GameBoardActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private QuestionViewModel questionViewModel;
    private PlayerViewModel playerViewModel;
    private List<LinearLayout> layoutList;
    private TextView playerPoints;
    private Drawable[] drawables;
    private ViewGroup viewGroup;
    private TextView playerName; //TODO: implement use

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        setViewGroup();
        findAndLoadLayout();
        setQuestionViewModel();
        setPlayerModel();
        setDrawables();
        setViewGroup();
    }

    private void setViewGroup() {
        viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
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

    private void setQuestionViewModel() {
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getListLiveData().observe(this, (List<List<Question>> lists) -> {
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
        inflateFragment(
                QuestionFragment.newInstance(key),
                QUESTION_FRAGMENT_TAG, true);
    }

    @Override
    public void displayResult(boolean isCorrect) {
        removeQuestionFragment();
        inflateFragment(
                ResultFragment.newInstance(isCorrect),
                RESULT_FRAGMENT_TAG,
                true);
        playerViewModel.updateToPlayerScore(questionViewModel.pointsAllocator(isCorrect));
    }

    @Override
    public void removeQuestionFragment() {
        removeFragment(QUESTION_FRAGMENT_TAG);
    }

    @Override
    public void removeResultFragment() {
        removeFragment(RESULT_FRAGMENT_TAG);
    }

    private void removeFragment(String fragmentTag) {
        Fragment destroyFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (destroyFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(destroyFragment).commit();
        }
    }

    @Override
    public void communicateQuestionStatus(boolean answered, String tag) {
        if (!answered) {
            markTileIsUnanswered(tag);
        } else {
            verifyTileIsAnswered(tag);
        }
    }

    public void verifyTileIsAnswered(String tag) {
        CardView cardview = viewGroup.findViewWithTag(tag);
        if (cardview != null) {
            cardview.setEnabled(false);
            Animations.tileAnsweredAnimate(cardview);
        }
    }

    public void markTileIsUnanswered(String tag) {
        CardView cardview = viewGroup.findViewWithTag(tag);
        cardview.setEnabled(true);
        Animations.tileUnansweredAnimate(cardview);
    }

    private void inflateFragment(Fragment fragment, String fragmentKey) {
        inflateFragment(fragment, fragmentKey, false);
    }

    private void inflateFragment(Fragment fragment, String fragmentTag, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragmentTag);
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}





