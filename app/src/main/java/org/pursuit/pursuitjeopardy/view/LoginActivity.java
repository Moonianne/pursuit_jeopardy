package org.pursuit.pursuitjeopardy.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.db.PlayerDBHelper;
import org.pursuit.pursuitjeopardy.model.Player;

public final class LoginActivity extends AppCompatActivity {
    private EditText login;
    private PlayerDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        login = findViewById(R.id.login_et);
        db = new PlayerDBHelper(getApplicationContext());

        //TODO make default activity after animation
        this.<ImageView>findViewById(R.id.login_bt).setOnClickListener(v -> {
            db.addPlayer(new Player(login.getText().toString(), 0));
            startActivity(new Intent(LoginActivity.this, GameBoardActivity.class));
        });
    }
}
