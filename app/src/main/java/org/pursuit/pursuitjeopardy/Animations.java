package org.pursuit.pursuitjeopardy;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Animations {
    private static Animation click;
    private static Animation expand;

    public static Animation getClick(View v){
        if(click == null){
            click = AnimationUtils.loadAnimation(v.getContext(),R.anim.tile_click);
        }
        return click;
    }

    public static Animation getExpand(View v){
        if(expand == null){
            expand = AnimationUtils.loadAnimation(v.getContext(),R.anim.tile_expand);
        }
        return expand;
    }

    public static void setTileAnimationsAtBoardInflation(final View v, final LinearLayout linearLayout){
        click = getClick(v);
        expand = getExpand(v);
        v.setEnabled(false);
        linearLayout.setClipChildren(false);
        click.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setElevation(1000000000);
                linearLayout.setElevation(999999998);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(expand);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        expand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.animate().alpha(0.0f).setStartDelay(500).setDuration(500);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setElevation(0);
                linearLayout.setElevation(0);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        v.startAnimation(click);
    }

    public static  void tileAnsweredAnimate(View v){
        v.setBackgroundColor(v.getResources().getColor(
                R.color.cardview_was_already_previously_selected_already_color));
        v.animate().alpha(1.0f).setStartDelay(1000).setDuration(1500);
    }

    public static void tileUnansweredAnimate(View v){
        v.setBackgroundColor(v.getResources().getColor(
                R.color.cardview_color));
        v.animate().alpha(1.0f).setStartDelay(800).setDuration(1500);
    }

    public static void launchedQuestionFragmentAnimate(View v){
        v.setAlpha(0);
        v.setElevation(999999999);
        v.animate().alpha(1.0f).setStartDelay(500).setDuration(500);
    }
}
