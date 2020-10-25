package com.example.android3_lesosn3.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android3_lesosn3.R;
import com.example.android3_lesosn3.data.local.PreferenceUtils;
import com.example.android3_lesosn3.data.models.Authorization;
import com.example.android3_lesosn3.data.network.auth_service.AuthService;
import com.example.android3_lesosn3.ui.App;
import com.example.android3_lesosn3.ui.MainActivity;

import okhttp3.Credentials;

public class AuthActivity extends AppCompatActivity {

    EditText editLogin;
    EditText editPass;
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        skip();

        init();
        onClicks();
    }

    private void skip() {
        if (!PreferenceUtils.getUser().isEmpty()){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void onClicks() {
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        String login = editLogin.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        String header = Credentials.basic(login,password);

        PreferenceUtils.saveUser(header);
        App.authService.authGet(header, new AuthService.AuthCallBack() {
            @Override
            public void onSuccess(Authorization authorization) {
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void init() {
        editLogin = findViewById(R.id.edit_log);
        editPass = findViewById(R.id.edit_pass);
        btn_enter = findViewById(R.id.btn_auth);
    }
}