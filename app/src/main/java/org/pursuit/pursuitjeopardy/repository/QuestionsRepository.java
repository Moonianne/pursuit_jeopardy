package org.pursuit.pursuitjeopardy.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import org.pursuit.pursuitjeopardy.enums.CategoryEnums;
import org.pursuit.pursuitjeopardy.enums.DifficultyEnums;
import org.pursuit.pursuitjeopardy.model.QuestionRequestModel;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionsRepository {
    private static QuestionsRepository repositorySingleInstance;

    private List<List<QuestionsModel>> lists;
    private MutableLiveData<List<List<QuestionsModel>>> liveData;


    private QuestionsRepository() {
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

    private void setList() {
        this.liveData = new MutableLiveData<>();
        this.lists = new ArrayList<>();
    }

    private void populateAllCategories() {
        for (int i = 0; i < 5; i++) {
            populateCategory(CategoryEnums.randomCategory(),DifficultyEnums.ANY_DIFFICULTY);
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

    private void parseRetrofitResponseList(List<QuestionsModel> questionsModels) {
        //TODO: Change to array[];
        // QuestionsModel[] questionsModelsArraywith3Questions = new QuestionsModel[3];
        List<QuestionsModel> questionsModelListWith3Questions =
                new ArrayList<>(Arrays.asList(new QuestionsModel[]{null, null, null}));
        for (QuestionsModel questions : questionsModels) {
            if (questions.getDifficulty().equals("easy")) {
                questionsModelListWith3Questions.add(0, questions);
            }
            if (questions.getDifficulty().equals("medium")) {
                questionsModelListWith3Questions.add(1, questions);
            }
            if (questions.getDifficulty().equals("hard")) {
                questionsModelListWith3Questions.add(2, questions);
            }
        }
        //TODO: separate this method to storeMethod
        lists.add(questionsModelListWith3Questions);
        liveData.setValue(lists);
    }

    public MutableLiveData<List<List<QuestionsModel>>> getLiveData() {
        return liveData;
    }

}
