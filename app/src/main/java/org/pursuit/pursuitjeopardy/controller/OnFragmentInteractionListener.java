package org.pursuit.pursuitjeopardy.controller;

import android.view.View;

public interface OnFragmentInteractionListener {
    void displayQuestion(String key);

    void displayResult(boolean isCorrect);

    void communicateQuestionStatus(boolean answered,String tag);
//
//    void verifyTileIsAnswered(String tag);
//
//    void markTileIsUnanswered(String tag);
}
