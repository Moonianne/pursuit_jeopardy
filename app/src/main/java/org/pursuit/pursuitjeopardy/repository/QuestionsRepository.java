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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionsRepository {

    private static QuestionsRepository repositorySingleInstance;
    private List<List<QuestionsModel>> lists;
    private MutableLiveData<List<List<QuestionsModel>>> liveData;
    private List<QuestionsModel> category1;
    private List<QuestionsModel> category2;
    private List<QuestionsModel> category3;
    private List<QuestionsModel> category4;
    private List<QuestionsModel> category5;

    private QuestionsRepository() {
        setList();
        populateAllCategories();
    }

    private void setList() {
        this.liveData = new MutableLiveData<>();
        this.lists = new ArrayList<>();
        this.category1 = new ArrayList<>();
        this.category2 = new ArrayList<>();
        this.category3 = new ArrayList<>();
        this.category4 = new ArrayList<>();
        this.category5 = new ArrayList<>();
    }

    public static QuestionsRepository getRepositorySingleInstance() {
        if (repositorySingleInstance != null) {
            return repositorySingleInstance;
        } else {
            repositorySingleInstance = new QuestionsRepository();
            return repositorySingleInstance;
        }
    }

    private void populateCategory1() {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        CategoryEnums.randomCategory(),
                        DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        List<QuestionsModel> questionResponse = response.body().getResults();
                        parseRetrofitResponseList(questionResponse);
                        Log.v("sasuke1", response.toString());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

    }

    private void populateCategory2() {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        CategoryEnums.randomCategory(),
                        DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        List<QuestionsModel> questionResponse = response.body().getResults();
                        category2 = parseRetrofitResponseList(questionResponse);
                        Log.v("sasuke2", response.toString());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

    }

    private void populateCategory3() {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        CategoryEnums.randomCategory(),
                        DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        List<QuestionsModel> questionResponse = response.body().getResults();
                        category3 = parseRetrofitResponseList(questionResponse);
                        Log.v("sasuke3", response.toString());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

    }

    private void populateCategory4() {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        CategoryEnums.randomCategory(),
                        DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        List<QuestionsModel> questionResponse = response.body().getResults();
                        category4 = parseRetrofitResponseList(questionResponse);
                        Log.v("sasuke4", response.toString());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

    }

    private void populateCategory5() {
        RetrofitSingleton.triviaApiCall(new QuestionRequestModel(
                        CategoryEnums.randomCategory(),
                        DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        List<QuestionsModel> questionResponse = response.body().getResults();
                        category5 = parseRetrofitResponseList(questionResponse);
                        Log.v("sasuke5", response.toString());
                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

    }

    private void populateAllCategories() {
        populateCategory1();
        populateCategory2();
        populateCategory3();
        populateCategory4();
        populateCategory5();
    }

    private List<QuestionsModel> parseRetrofitResponseList(List<QuestionsModel> questionsModels) {
        List<QuestionsModel> questionsModelListWith3Questions = new ArrayList<>();
        questionsModelListWith3Questions.add(null);
        questionsModelListWith3Questions.add(null);
        questionsModelListWith3Questions.add(null);
        for (QuestionsModel questions : questionsModels) {
            if (questions.getDifficulty().equals("easy")){
                questionsModelListWith3Questions.add(0,questions);
            }
            if (questions.getDifficulty().equals("medium")) {
                questionsModelListWith3Questions.add(1,questions);
            }
            if (questions.getDifficulty().equals("hard")) {
                questionsModelListWith3Questions.add(2,questions);
            }

        }
        lists.add(questionsModelListWith3Questions);
        liveData.setValue(lists);
        return questionsModelListWith3Questions;
    }

    public MutableLiveData<List<List<QuestionsModel>>> getLiveData(){
        return liveData;
    }

}
