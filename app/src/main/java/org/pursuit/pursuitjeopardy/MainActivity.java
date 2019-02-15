package org.pursuit.pursuitjeopardy;

// API SOURCE: https://opentdb.com/api_config.php

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.pursuit.pursuitjeopardy.enums.TriviaApiCallCategoryEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallDifficultyEnums;
import org.pursuit.pursuitjeopardy.enums.TriviaApiCallTypeEnums;
import org.pursuit.pursuitjeopardy.model.TriviaResponseModel;
import org.pursuit.pursuitjeopardy.network.RetrofitSingleton;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "Brahh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
