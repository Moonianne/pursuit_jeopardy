package org.pursuit.pursuitjeopardy.view;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;

import java.util.List;

import static android.support.v4.widget.TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration;

public class BoardInflater {

    private LinearLayout linearLayout;
    private List<QuestionsModel> questionsModels;
    private OnTileClickedListener listener;

    public BoardInflater(LinearLayout linearLayout, List<QuestionsModel> questionsModels) {
        this.linearLayout = linearLayout;
        this.questionsModels = questionsModels;
    }

    public void populateLayout() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f);
        layoutParams.setMargins(16, 16, 16, 16);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.2f);

        TextView category = new TextView(linearLayout.getContext());
        category.setLayoutParams(textParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            category.setAutoSizeTextTypeUniformWithConfiguration(
                    1, 17, 1, TypedValue.COMPLEX_UNIT_DIP);
        }else{
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    category,1, 17, 1, TypedValue.COMPLEX_UNIT_DIP);
        }
        category.setText(questionsModels.get(0).getCategory());
        category.setTypeface(Typeface.DEFAULT_BOLD);
        category.setTextColor(linearLayout.getResources().getColor(R.color.category_color));

        category.setGravity(Gravity.CENTER_VERTICAL);
        category.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        linearLayout.addView(category);

        for (int i = 0; i < 3; i++) {



            View pointsTileCardView = LayoutInflater
                    .from(linearLayout.getContext())
                    .inflate(R.layout.trivia_itemview,linearLayout,false);

//            final CardView pointsTileCardView = new CardView(linearLayout.getContext());
            pointsTileCardView.setClickable(true);
            pointsTileCardView.setFocusable(true);
            pointsTileCardView.setBackgroundColor(linearLayout.getResources().getColor(R.color.cardview_color));
            pointsTileCardView.setId(questionsModels.get(i).getQuestion().length());
            pointsTileCardView.setLayoutParams(layoutParams);
            pointsTileCardView.setTag(questionsModels.get(i));
            pointsTileCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTileClicked(v);
                }
            });
            linearLayout.addView(pointsTileCardView);
        }
    }

    public interface OnTileClickedListener {
        void onTileClicked(View view);
    }

    public void setOnTileSelectedListener(OnTileClickedListener listener) {
        this.listener = listener;
    }
}
