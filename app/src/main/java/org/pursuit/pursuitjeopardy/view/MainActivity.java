package org.pursuit.pursuitjeopardy.view;

// API SOURCE: https://opentdb.com/api_config.php

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation spin =
                AnimationUtils
                        .loadAnimation(getApplicationContext(), R.anim.logo_spin);
        spin.setAnimationListener(getAnimationListener());
        this.<ImageView>findViewById(R.id.logo)
                .startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.logo_spin));
        this.<ImageView>findViewById(R.id.logo).startAnimation(spin);
    }

    private Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //No-op
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startBoard();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //No-op
            }
        };
    }

    public void startBoard() {
        startActivity(
                new Intent(this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
