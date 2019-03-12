package org.pursuit.pursuitjeopardy.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.model.Player;
import org.pursuit.pursuitjeopardy.view.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText login;
    private ImageView loginBT;
    private PlayerDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        login = findViewById(R.id.login_et);
        loginBT = findViewById(R.id.login_bt);
        db = new PlayerDBHelper(getApplicationContext());

        //TODO make default activity after animation
        loginBT.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Player player = new Player(login.getText().toString(), 0);
            db.addPlayer(player);
            startActivity(intent);
        });
    }
}
