package org.pursuit.pursuitjeopardy.network;

import android.support.annotation.NonNull;


import org.pursuit.pursuitjeopardy.model.TokenModel;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaService {

    @GET("api.php?&amount=15")
    Call<TriviaResponseModel> getTrivia(@Query("category") @NonNull String category,
                                        @Query("difficulty") @NonNull String difficulty);

    /**
     * A token is used by the API to track what questions they have sent to us already, preventing duplicate questions until
     * we have reached the limit of the query. Will expand when necessary.
     */
    @GET("api_token.php?command=request")
    Call<TokenModel> getToken();

}
