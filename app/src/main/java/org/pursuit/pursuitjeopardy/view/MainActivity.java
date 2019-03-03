package org.pursuit.pursuitjeopardy.view;

// API SOURCE: https://opentdb.com/api_config.php

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.controller.OnFragmentInteractionListener;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBoard();
    }

    @Override
    public void startBoard() {
        startActivity(new Intent(this, GameBoardActivity.class));
    }

    @Override
    public void displayQuestion(QuestionsModel question) {
        //No-op
    }

    private void inflateFragment(Fragment fragment) {
        inflateFragment(fragment, false);
    }

    private void inflateFragment(Fragment fragment, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, fragment);
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
