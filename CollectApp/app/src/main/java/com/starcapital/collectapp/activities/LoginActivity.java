package com.starcapital.collectapp.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.utilities.DialogUtility;
import com.starcapital.collectapp.utilities.LoginHelper;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;

public class LoginActivity extends AppCompatActivity {

    int LOGIN_RESULT_CODE = 1;
    Button buttonLogin;
    LoginHelper loginHelper;
    DialogUtility dialogUtility;
    PendingIntent pendingIntentSuccess, pendingIntentCancel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginHelper.authenticate();
            }
        });

    }

    private void init() {

        buttonLogin = findViewById(R.id.login_button);
        context = LoginActivity.this;
        dialogUtility = new DialogUtility(context);
        pendingIntentSuccess = PendingIntent.getActivity(LoginActivity.this, 0, new Intent(LoginActivity.this, MainActivity.class), 0);
        pendingIntentCancel = PendingIntent.getActivity(LoginActivity.this, 0, new Intent(LoginActivity.this, LoginActivity.class), 0);
        loginHelper = new LoginHelper(LoginActivity.this, pendingIntentSuccess, pendingIntentCancel, true);

    }
    //This method receives the data from the browser when login is successful or canceled and acts accordingly
    //The method adds the intent data which will be received in the pendingIntent for when login is successful
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_RESULT_CODE) {
            AuthorizationResponse resp = AuthorizationResponse.fromIntent(data);
            AuthorizationException ex = AuthorizationException.fromIntent(data);
            loginHelper.showData(resp, ex);
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            finish();
            // ... process the response or exception ...
        }
    }

}
