package org.pursuit.pursuitjeopardy.viewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import org.pursuit.pursuitjeopardy.model.QuestionsModel;
import org.pursuit.pursuitjeopardy.repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class QuestionViewModel extends ViewModel {

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

    public String[] getAnswer(String key) {
        QuestionsModel questionsModel = questionMap.get(key);
        if (questionsModel.getCorrect_answer().equals("true") ||
                questionsModel.getCorrect_answer().equals("false")) {
            return new String[]{"True", "False"};
        }
        List<String> answers = questionsModel.getIncorrect_answers();
        Collections.shuffle(answers);
        answers.add(questionsModel.getCategory());
        return (String[]) answers.toArray();
    }
}
