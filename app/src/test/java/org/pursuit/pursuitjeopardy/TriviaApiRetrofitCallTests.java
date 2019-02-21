package org.pursuit.pursuitjeopardy;

import android.util.Log;

import org.junit.Ignore;
import org.pursuit.pursuitjeopardy.enums.CategoryEnums;
import org.pursuit.pursuitjeopardy.enums.DifficultyEnums;
import org.pursuit.pursuitjeopardy.model.QuestionRequestModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.pursuit.pursuitjeopardy.MainActivity.DEBUG_TAG;

public class TriviaApiRetrofitCallTests {


    @Ignore
    public void exampleSetUpForTriviaApiResponse(){
        /** paste the code under this line into MainActivity , run it, and try it yourself! =]
         */
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
