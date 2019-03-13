package org.pursuit.pursuitjeopardy.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import org.pursuit.pursuitjeopardy.enums.CategoryEnums;
import org.pursuit.pursuitjeopardy.enums.DifficultyEnums;
import org.pursuit.pursuitjeopardy.model.Player;
import org.pursuit.pursuitjeopardy.model.QuestionRequestModel;
import org.pursuit.pursuitjeopardy.model.Question;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepository {
    private static QuestionsRepository repositorySingleInstance;

    private MutableLiveData<List<List<Question>>> liveData;
    private List<List<Question>> lists;
    private Map questionsMap;

    private QuestionsRepository() {
        setPlayer();
        setList();
        populateAllCategories();
    }

    public static QuestionsRepository getRepositorySingleInstance() {
        if (repositorySingleInstance != null) {
            return repositorySingleInstance;
        } else {
            repositorySingleInstance = new QuestionsRepository();
            return repositorySingleInstance;
        }
    }

    public Player setPlayer() {
        return new Player();
    }

    private void setList() {
        this.liveData = new MutableLiveData<>();
        this.lists = new ArrayList<>();
        this.questionsMap = new HashMap<String, Question>();
    }

    private void populateAllCategories() {
        final Set<CategoryEnums> selectedCategory = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            CategoryEnums random = CategoryEnums.randomCategory();
            while (selectedCategory.contains(random)) {
                random = CategoryEnums.randomCategory();
            }
            selectedCategory.add(random);
            populateCategory(random, DifficultyEnums.ANY_DIFFICULTY);
        }
    }

    private void populateCategory(CategoryEnums random, DifficultyEnums difficultyEnums) {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        random,
                        difficultyEnums),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        parseRetrofitResponseList(response.body().getResults());
                        Log.v("sasuke1", response.toString());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });
    }

    private void parseRetrofitResponseList(List<Question> questions) {
        List<Question> questionListWith3Questions =
                new ArrayList<>(Arrays.asList(new Question[]{null, null, null}));
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            String questionDifficulty = currentQuestion.getDifficulty();

            if (questionDifficulty.equals("easy") && questionListWith3Questions.get(0) == null) {
                questionListWith3Questions.add(0, currentQuestion);
                questionsMap.put(currentQuestion.getCategory() + questionDifficulty, currentQuestion);
            }
            if (questionDifficulty.equals("medium") &&
                    questionListWith3Questions.get(1) == null) {
                questionListWith3Questions.add(1, currentQuestion);
                questionsMap.put(currentQuestion.getCategory() + questionDifficulty, currentQuestion);
            }
            if (questionDifficulty.equals("hard") &&
                    questionListWith3Questions.get(2) == null) {
                questionListWith3Questions.add(2, currentQuestion);
                questionsMap.put(currentQuestion.getCategory() + questionDifficulty, currentQuestion);
            }
        }
        lists.add(questionListWith3Questions);
        liveData.setValue(lists);
    }

    public MutableLiveData<List<List<Question>>> getLiveData() {
        return liveData;
    }

    public Map<String, Question> getQuestionsMap() {
        return questionsMap;
    }
}
