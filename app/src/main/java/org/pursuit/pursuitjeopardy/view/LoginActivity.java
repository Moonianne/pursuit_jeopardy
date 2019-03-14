package org.pursuit.pursuitjeopardy.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import org.pursuit.pursuitjeopardy.R;
import org.pursuit.pursuitjeopardy.viewModel.PlayerViewModel;

public final class LoginActivity extends AppCompatActivity {
    private EditText login;
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        findViews();
        setViewModel();
        buttonOnClickListener();
    }

    private void findViews() {
        login = findViewById(R.id.login_et);
    }

    private void setViewModel() {
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
    }

    private void buttonOnClickListener() {
        this.<ImageView>findViewById(R.id.login_bt).setOnClickListener(v -> {
            playerViewModel.setPlayer(login.getText().toString());
            playerViewModel.addPlayerToDatabase();
            startActivity(new Intent(LoginActivity.this, GameBoardActivity.class));
        });
    }
}
