package org.pursuit.pursuitjeopardy.view;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.pursuitjeopardy.model.QuestionsModel;

import java.util.List;

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

        TextView category = new TextView(linearLayout.getContext());
        category.setText(questionsModels.get(0).getCategory());
        category.setTextSize(16);
        category.setMaxLines(2);
        category.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayout.addView(category);

        for (int i = 0; i < 3; i++) {
            final CardView pointsTileCardView = new CardView(linearLayout.getContext());
            pointsTileCardView.setClickable(true);
            pointsTileCardView.setFocusable(true);
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
