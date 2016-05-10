package com.app.carapitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.carapitest.R;
import com.app.carapitest.utils.Utilities;

public class LoginActivity extends Activity{

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.hideSoftKeyboard(LoginActivity.this);
                boolean hasError = false;
                if (username.getText().toString().isEmpty()) {
                    username.setError("Please enter your username");
                    hasError = true;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Please enter your password");
                    hasError = true;
                }
                if (!hasError) {
                    startLogin();
                }
            }
        });
    }

    private void startLogin() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        findViewById(R.id.main_layout).setVisibility(View.INVISIBLE);
        startActivity(new Intent(this, CarsListActivity.class));
    }

}

