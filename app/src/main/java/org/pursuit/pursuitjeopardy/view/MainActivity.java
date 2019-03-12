package org.pursuit.pursuitjeopardy.view;

// API SOURCE: https://opentdb.com/api_config.php

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);

        Animation spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_spin);
        spin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                startBoard();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        logo.startAnimation(spin);
    }

    public void startBoard() {
        startActivity(new Intent(this, GameBoardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
