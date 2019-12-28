package com.starcapital.collectapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.starcapital.collectapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    String password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = editTextPassword.getText().toString();
                username = editTextUsername.getText().toString();

                validate(username, password);

            }
        });

        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextUsername.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextPassword.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void init() {

        editTextPassword = findViewById(R.id.login_password);
        editTextUsername = findViewById(R.id.login_username);
        buttonLogin = findViewById(R.id.login_button);

    }

    private void validate(String user, String pass) {

        if (pass.isEmpty() && user.isEmpty()) {
            editTextUsername.setError("Please enter your Username");
            editTextPassword.setError("Please enter your Password");
        } else if (pass.isEmpty()) {
            editTextPassword.setError("Please enter your Password");
        } else if (user.isEmpty()) {
            editTextUsername.setError("Please enter your Username");
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

}
