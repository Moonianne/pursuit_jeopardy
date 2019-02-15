package org.pursuit.pursuitjeopardy.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.pursuit.pursuitjeopardy.MainActivity;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallCategoryEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallDifficultyEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallTypeEnums;
import org.pursuit.pursuitjeopardy.model.TokenModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
     * This callback's response return a TriviaResponseModel object that contains a list of
     * QuestionModel objects which are the actual questions and there states.
     * @param amount     must be a String number i.e : "10".
     * @param category   use Enums from TriviaApiCallCategoryEnums.
     * @param difficulty use Enums from TriviaApiCallDifficultyEnums.
     * @param type       use Enums from TriviaApiCallTypeEnums.
     * @param callback   pass in new Callback of Object TriviaResponseModel.
     */
    public void triviaApiCall(@NonNull String amount,
                              @NonNull TriviaApiCallCategoryEnums category,
                              @NonNull TriviaApiCallDifficultyEnums difficulty,
                              @NonNull TriviaApiCallTypeEnums type,
                              @NonNull Callback<TriviaResponseModel> callback) {
        if (category == null || amount == null || difficulty == null || type == null || callback == null) {
            throw new NullPointerException(MainActivity.DEBUG_TAG + " All parameters must not be Null Values!");
        }
        try {
            Integer.valueOf(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException(MainActivity.DEBUG_TAG + " @param(amount) must be a String number!");
        }
        RetrofitSingleton.getInstance()
                .create(TriviaService.class)
                .getTrivia(amount, category.getLinkTranslation(), difficulty.getLinkTranslation(), type.getLinkTranslation())
                .enqueue(callback);
    }

    /**
     * This callback's response would return a TokenModel object that contains the token we would use to track
     * a certain session to prevent the API from sending duplicate questions.
     * @param callback pass new Callback of Object TokenModel.
     */
    public void getToken(@NonNull Callback<TokenModel> callback) {
        if (callback == null) {
            throw new NullPointerException(MainActivity.DEBUG_TAG + " @param callback must not be null!");
        }
        RetrofitSingleton.getInstance()
                .create(TriviaService.class)
                .getToken()
                .enqueue(callback);
    }

}
