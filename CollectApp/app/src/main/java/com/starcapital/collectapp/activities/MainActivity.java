package com.starcapital.collectapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.services.NetworkCalls;
import com.starcapital.collectapp.utilities.Utility;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    Utility utility;
    AuthorizationService authService;
    AuthState authState;
    RelativeLayout layoutDeposit, layoutLoans, layoutWithdrawal, layoutCreateAccount, layoutTransactions, layoutAccounts;
    NetworkCalls networkCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //        The intent data sent from the Login Page is received here and updated to the AuthState
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
        if (resp != null) {
            // authorization completed
            System.out.println("AUTH RESP----" + resp.toString());
            System.out.println("TOKEN Request----" + resp.authorizationCode);
            authState.update(resp, ex);
            utility.writeAuthState(authState);
        } else {
            // authorization failed, check ex for more details
            System.out.println("AUTH EX-----" + ex);
        }

        //The authorization response which is received from the intent data is used to perform an exchange for the Token Response
        //The Token Response contains the access token, the id token, refresh token,
        // scope, token type, the access token expiry time and additional params
        if (resp != null) {
            authService.performTokenRequest(resp.createTokenExchangeRequest(), (response, ex1) -> {
                if (response != null) {
                    System.out.println("TOKEN Request----" + response.accessToken);
                    System.out.println("ID Request----" + response.idToken);
                    authState.update(response, ex1);
                    utility.writeAuthState(authState);
                    utility.setLogout(false);
                } else {
                    assert ex1 != null;
                    System.out.println("EXCEPTION----" + ex1.toString());
                    // authorization failed, check ex for more details
                }
            });
        } else {
            // authorization failed, check ex for more details
            // TODO handle exception
            if (ex != null) {
                Log.d("AUTHORIZATION FAILED --", ex.toString());
            }
        }


        layoutDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                intent.putExtra("transaction", 1);
                startActivity(intent);
            }
        });

//        layoutWithdrawal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
//                intent.putExtra("transaction", 2);
//                startActivity(intent);
//            }
//        });
//
//        layoutLoans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
//                intent.putExtra("transaction", 3);
//                startActivity(intent);
//            }
//        });

        layoutCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        layoutAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });

        layoutTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllTransactionsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        authService = new AuthorizationService(this);
        authState = new AuthState();
        layoutAccounts = findViewById(R.id.accounts_layout);
        layoutCreateAccount = findViewById(R.id.create_account_layout);
        layoutLoans = findViewById(R.id.loans_layout);
        layoutWithdrawal = findViewById(R.id.withdrawal_layout);
        layoutDeposit = findViewById(R.id.deposit_layout);
        layoutTransactions = findViewById(R.id.transactions_layout);
        utility = new Utility(MainActivity.this);
        networkCalls = new NetworkCalls(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                utility.setLogout(true);
            case R.id.action_setups:
                try {
                    networkCalls.saveCardTypes();
                    networkCalls.saveBranches();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }

        return true;
    }
}
