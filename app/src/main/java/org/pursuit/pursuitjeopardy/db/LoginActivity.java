package org.pursuit.pursuitjeopardy.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;

public class LoginActivity extends AppCompatActivity {
    private EditText login;
    private ImageView loginBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_et);
        loginBT = findViewById(R.id.login_bt);
    }
}
