package org.pursuit.pursuitjeopardy;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Animations {
    Context context;
    Animation click;
    Animation expand;

    public Animation getClick() {
        return click;
    }

    public Animations(Context context) {
        this.context = context;
        click = AnimationUtils.loadAnimation(context, R.anim.tile_click);
        expand = AnimationUtils.loadAnimation(context, R.anim.tile_expand);

    }

    public CardView setAnimations(final CardView cardview, final LinearLayout linearLayout){

        cardview.setAnimation(click);

        click.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                cardview.setElevation(1000000000);
                linearLayout.setElevation(999999999);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardview.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        expand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardview.setElevation(0);
                linearLayout.setElevation(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return cardview;
    }
}
