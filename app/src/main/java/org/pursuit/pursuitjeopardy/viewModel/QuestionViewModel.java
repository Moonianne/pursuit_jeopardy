package org.pursuit.pursuitjeopardy.viewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import org.pursuit.pursuitjeopardy.model.PlayerModel;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;
import org.pursuit.pursuitjeopardy.repository.QuestionsRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;


public final class QuestionViewModel extends ViewModel {

    private static final String TAG = "org.pursuit.viewModel";

    private QuestionsRepository questionsRepository;
    private LiveData<List<List<QuestionsModel>>> listLiveData;
    private Map<String, QuestionsModel> questionMap;
    private String currentKey;

    public QuestionViewModel() {
        questionsRepository = QuestionsRepository.getRepositorySingleInstance();
        listLiveData = questionsRepository.getLiveData();
        questionMap = questionsRepository.getQuestionsMap();
    }

    public LiveData<List<List<QuestionsModel>>> getListLiveData() {
        return listLiveData;
    }

    public Map<String, QuestionsModel> getQuestionMap() {
        return questionMap;
    }

    public String getQuestion(String key) {
        return questionMap.get(key).getQuestion();
    }

    public String getQuestionDifficulty(String key){ return questionMap.get(key).getDifficulty();}

    public String[] getAnswers(String key) {
        QuestionsModel questionsModel = questionMap.get(key);
        if (questionsModel.getCorrect_answer().equals("true") ||
                questionsModel.getCorrect_answer().equals("false")) {
            return new String[]{"True", "False"};
        }
        List<String> answers = questionsModel.getIncorrect_answers();
        String[] result = new String[answers.size()];
        answers.add(questionsModel.getCorrect_answer());
        Collections.shuffle(answers);
        Log.d(TAG, "getAnswers: " + Arrays.toString(answers.toArray(result)));
        return answers.toArray(result);
    }

    public String getCorrect(String key) {
        QuestionsModel questionsModel = questionMap.get(key);
        return questionsModel.getCorrect_answer();
    }

    public int pointsAllocator(boolean isCorrect) {
        if (isCorrect) {
            switch (questionMap.get(currentKey).getDifficulty()) {
                case "easy":
                    Log.d("difficulty easy",
                            questionMap.get(currentKey).getQuestion()
                                    + " " + questionMap.get(currentKey).getDifficulty() );
                    return 200;
                case "medium":
                    Log.d("difficulty med",
                            questionMap.get(currentKey).getQuestion()
                                    + " " + questionMap.get(currentKey).getDifficulty() );
                    return 400;
                case "hard":
                    Log.d("difficulty hard",
                            questionMap.get(currentKey).getQuestion()
                                    + " " + questionMap.get(currentKey).getDifficulty() );
                    return 600;
                default:
                    return 0;
            }
        }
        return 0;
    }

    public void setCurrentQuestionKey(String key){
        currentKey = key;
    }

    public String getCurrentQuestionKey(){
        return currentKey;
    }



}
