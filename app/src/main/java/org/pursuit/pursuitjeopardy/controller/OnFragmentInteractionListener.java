package org.pursuit.pursuitjeopardy.controller;

public interface OnFragmentInteractionListener {
    String QUESTION_FRAGMENT_TAG = "question";
    String RESULT_FRAGMENT_TAG = "result";

    void displayQuestion(String key);

    void displayResult(boolean isCorrect);

    void removeQuestionFragment();

    void removeResultFragment();

    void communicateQuestionStatus(boolean answered,String tag);

    void reportGameStatus(boolean gameFinished);
}
