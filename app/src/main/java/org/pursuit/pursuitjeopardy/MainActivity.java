package org.pursuit.pursuitjeopardy;

// API SOURCE: https://opentdb.com/api_config.php

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.pursuit.pursuitjeopardy.enums.CategoryEnums;
import org.pursuit.pursuitjeopardy.enums.DifficultyEnums;
import org.pursuit.pursuitjeopardy.model.QuestionRequestModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "Brahh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrofitSingleton().triviaApiCall(
                new QuestionRequestModel(CategoryEnums.HISTORY, DifficultyEnums.ANY_DIFFICULTY),
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        Log.d(DEBUG_TAG, response.message());
                        Log.d(DEBUG_TAG, call.request().toString());
                        Log.d(DEBUG_TAG, response.body().getResults().get(0).getQuestion());

                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {
                        Log.d(DEBUG_TAG, t.toString());
                        Log.d(DEBUG_TAG, t.getMessage());
                        t.printStackTrace();
                    }
                });

    }
}
