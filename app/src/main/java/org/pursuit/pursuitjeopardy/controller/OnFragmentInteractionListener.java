package org.pursuit.pursuitjeopardy.controller;

import android.view.View;

public interface OnFragmentInteractionListener {
    void displayQuestion(String key);

    void displayResult(boolean isCorrect);

    void checkTileIsAnswered(boolean isAnswered, String key);
}
