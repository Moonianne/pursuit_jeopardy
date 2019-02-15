package org.pursuit.pursuitjeopardy;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallCategoryEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallDifficultyEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallTypeEnums;
import org.pursuit.pursuitjeopardy.model.QuestionsModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.pursuit.pursuitjeopardy.MainActivity.DEBUG_TAG;

public class TriviaApiRetrofitCallTests {

    private RetrofitSingleton retrofitSingleton;

    @Ignore
    public void exampleSetUpForTriviaApiResponse(){
        // paste this code into MainActivity , run it, and try it yourself! =]
        new RetrofitSingleton().triviaApiCall("10",
                TriviaApiCallCategoryEnums.ENTERTAINMENT_COMICS,
                TriviaApiCallDifficultyEnums.ANY_DIFFICULTY,
                TriviaApiCallTypeEnums.MULTIPLE,
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        Log.d(DEBUG_TAG,response.message());
                        Log.d(DEBUG_TAG,call.request().toString());
                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {
                        Log.d(DEBUG_TAG,t.toString());
                        Log.d(DEBUG_TAG,t.getMessage());
                        t.printStackTrace();
                    }
                });
    }

    @Before
    public void setUp(){
        retrofitSingleton = new RetrofitSingleton();
    }

    @Test
    public void testTriviaApiResponse(){
        //Given
        String testAmount = "15";
        TriviaApiCallCategoryEnums testCategory = TriviaApiCallCategoryEnums.CELEBRITIES;
        TriviaApiCallDifficultyEnums testDifficulty = TriviaApiCallDifficultyEnums.HARD;
        TriviaApiCallTypeEnums testType = TriviaApiCallTypeEnums.TRUE_OR_FALSE;

        final int expectedSizeOfList = 15;
        final String expectedDifficulty = "easy";
        final String expectedCategory = "Celebrities";
        final String expectedType = "boolean";

        //When
        retrofitSingleton.triviaApiCall(testAmount, testCategory, testDifficulty, testType,
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                        TriviaResponseModel triviaResponseModel = response.body();
                        QuestionsModel questionsModel = triviaResponseModel.getResults().get(0);

                        int sizeResults = triviaResponseModel.getResults().size();
                        String difficultyResults = questionsModel.getDifficulty();
                        String categoryResults = questionsModel.getCategory();
                        String typeResults = questionsModel.getType();

                        //Then
                        Assert.assertEquals(expectedSizeOfList,sizeResults);
                        Assert.assertEquals(expectedDifficulty, difficultyResults);
                        Assert.assertEquals(expectedCategory,categoryResults);
                        Assert.assertEquals(expectedType,typeResults);
                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });
    }

    @Test(expected = NullPointerException.class)
    public void testTriviaApiResponseWithNullInput() {
        //Given
        String testAmount = null;
        TriviaApiCallCategoryEnums testCategory = TriviaApiCallCategoryEnums.ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA;
        TriviaApiCallDifficultyEnums testDifficulty = TriviaApiCallDifficultyEnums.HARD;
        TriviaApiCallTypeEnums testType = null;

        //When
        retrofitSingleton.triviaApiCall(testAmount, testCategory, testDifficulty, testType,
                new Callback<TriviaResponseModel>() {
                    @Override
                    public void onResponse(Call<TriviaResponseModel> call, Response<TriviaResponseModel> response) {
                    }

                    @Override
                    public void onFailure(Call<TriviaResponseModel> call, Throwable t) {

                    }
                });

        //Then throw a NullPointerException, Check details using DEBUG tag from main activity.

    }


}
