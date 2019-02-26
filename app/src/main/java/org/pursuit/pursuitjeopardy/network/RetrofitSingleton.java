package org.pursuit.pursuitjeopardy.network;

import android.support.annotation.NonNull;

import org.pursuit.pursuitjeopardy.model.QuestionRequestModel;
import org.pursuit.pursuitjeopardy.model.TokenModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit instance;

    /**
     * single instance is private because we gain better control over the parameter
     * inputs if we encapsulate it's use into a public method that can check parameters.
     * also saves us lines of code in boiler plate. Refer to test for example implementation.
     */

    private static Retrofit getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

    /**
     * This callback's response returns a TriviaResponseModel object that contains a list of
     * QuestionModel objects which are the actual questions and there states.
     *
     * SEE TriviaApiRetrofitCallTests FOR EXAMPLE CALL!
     *
     * @param requestModel pass in new QuestionRequestModel object with desired enum descriptions of the particular call.
     * @param callback     pass in new Callback of Object TriviaResponseModel.
     */
    public void triviaApiCall(@NonNull QuestionRequestModel requestModel,
                              @NonNull Callback<TriviaResponseModel> callback) {
        RetrofitSingleton.getInstance()
                .create(TriviaService.class)
                .getTrivia(requestModel.categoryEnums, requestModel.difficultyEnums)
                .enqueue(callback);
    }

    /**
     * This callback's response would return a TokenModel object that contains the token we would use to track
     * a certain session to prevent the API from sending duplicate questions.
     *
     * @param callback pass new Callback of Object TokenModel.
     */
    public void getToken(@NonNull Callback<TokenModel> callback) {
        RetrofitSingleton.getInstance()
                .create(TriviaService.class)
                .getToken()
                .enqueue(callback);
    }

}
