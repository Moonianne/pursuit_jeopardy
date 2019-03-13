package org.pursuit.pursuitjeopardy.view;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.Animations;
import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.model.Question;

import java.util.List;

public class BoardInflater {

    private LinearLayout linearLayout;
    private List<Question> questions;
    private OnTileClickedListener listener;
    private Drawable[] tileAmountDrawables;

    public BoardInflater(LinearLayout linearLayout, List<Question> questions, Drawable[] tileAmountDrawables) {
        this.linearLayout = linearLayout;
        this.questions = questions;
        this.tileAmountDrawables = tileAmountDrawables;
    }

    public void populateLayout() {
        TextView category = createTextView();
        linearLayout.addView(category);
        createCardViews();
    }

    private void createCardViews() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f);
        layoutParams.setMargins(16, 16, 16, 16);

        for (int i = 0; i < 3; i++) {
            final CardView pointsTileCardView = (CardView) LayoutInflater
                    .from(linearLayout.getContext())
                    .inflate(R.layout.trivia_itemview, linearLayout, false);
            pointsTileCardView.getChildAt(0).setBackground(tileAmountDrawables[i]);
            pointsTileCardView.setClickable(true);
            pointsTileCardView.setFocusable(true);
            pointsTileCardView.setBackgroundColor(linearLayout.getResources().getColor(R.color.cardview_color));
            pointsTileCardView.setLayoutParams(layoutParams);
            pointsTileCardView.setTag(questions.get(i).getCategory() + questions.get(i).getDifficulty());
            pointsTileCardView.setOnClickListener(v -> {
                listener.onTileClicked(v);
                Animations.setTileAnimationsAtBoardInflation(
                        pointsTileCardView, linearLayout);

            });
            linearLayout.addView(pointsTileCardView);
        }
    }

    private TextView createTextView() {
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.2f);
        TextView category = new TextView(linearLayout.getContext());
        category.setLayoutParams(textParams);
        category.setPadding(8, 8, 8, 8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            category.setAutoSizeTextTypeUniformWithConfiguration(
                    1, 17, 1, TypedValue.COMPLEX_UNIT_DIP);
        } else {
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    category, 1, 17, 1, TypedValue.COMPLEX_UNIT_DIP);
        }
        category.setText(questions.get(0).getCategory());
        category.setTypeface(Typeface.DEFAULT_BOLD);
        category.setTextColor(linearLayout.getResources().getColor(R.color.category_color));
        category.setGravity(Gravity.CENTER_VERTICAL);
        category.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return category;
    }

    public interface OnTileClickedListener {
        void onTileClicked(View view);
    }

    public void setOnTileSelectedListener(OnTileClickedListener listener) {
        this.listener = listener;
    }

}
