package org.pursuit.pursuitjeopardy.viewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

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

    public String qetQuestionDifficulty(String key){ return questionMap.get(key).getDifficulty();}

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

    public int retrievePoints(boolean isCorrect, String questionDifficulty) {
        if (isCorrect) {
            switch (questionDifficulty) {
                case "easy":
                    return 200;
                case "medium":
                    return 400;
                case "hard":
                    return 600;
                default:
                    return 0;
            }
        }
        return -1;
    }
}
