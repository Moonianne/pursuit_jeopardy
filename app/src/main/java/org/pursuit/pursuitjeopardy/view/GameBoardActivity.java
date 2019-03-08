package org.pursuit.pursuitjeopardy.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.viewModel.QuestionViewModel;


import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;


public final class GameBoardActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private QuestionViewModel viewModel;
    private List<LinearLayout> layoutList;
    private Drawable[] drawables;
    static final String QUESTION_FRAGMENT_TAG = "question";
    static final String RESULT_FRAGMENT_TAG = "result";
    private ViewGroup viewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        setClipChildren();
        findAndLoadLayout();
        setViewModel();
        setDrawables();
    }

    private void setClipChildren() {
        viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setClipChildren(false);
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

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        viewModel.getListLiveData().observe(this, lists -> {
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

    private void setDrawables() {
        drawables = new Drawable[]{getResources().getDrawable(R.drawable.n200),
                getResources().getDrawable(R.drawable.n400),
                getResources().getDrawable(R.drawable.n600)};
    }

    @Override
    public void displayQuestion(String key) {
        Fragment fragment = QuestionFragment.newInstance(key);
        inflateFragment(fragment, QUESTION_FRAGMENT_TAG, true);

    }

    @Override
    public void displayResult(boolean isCorrect) {
        Fragment fragment = ResultFragment.newInstance(isCorrect);
        FragmentManager fragmentManager = getSupportFragmentManager();


        Fragment destroyFragment = fragmentManager.findFragmentByTag(QUESTION_FRAGMENT_TAG);

        if (destroyFragment != null) {
            fragmentManager.beginTransaction().remove(destroyFragment).commit();
        }
        inflateFragment(fragment, RESULT_FRAGMENT_TAG, true);

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
            Log.d("cardviewverify", cardview.getTag().toString());
            cardview.setBackgroundColor(cardview.getResources().getColor(
                    R.color.cardview_was_already_previously_selected_already_color));
            cardview.animate().alpha(1.0f).setStartDelay(1000).setDuration(1500);
        }
    }


    public void markTileIsUnanswered(String tag) {
        CardView cardview = viewGroup.findViewWithTag(tag);
        cardview.setEnabled(true);
        Log.d("cardview", cardview.getTag().toString());
        cardview.setBackgroundColor(cardview.getResources().getColor(
                R.color.cardview_color));
        cardview.animate().alpha(1.0f).setStartDelay(800).setDuration(1500);
    }


    private void inflateFragment(Fragment fragment, String fragmentKey) {
        inflateFragment(fragment, fragmentKey, false);
    }

    private void inflateFragment(Fragment fragment, String fragmentKey, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragmentKey);
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}



