package com.starcapital.collectapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.starcapital.collectapp.R;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutDeposit, layoutLoans, layoutWithdrawal, layoutCreateAccount, layoutTransactions, layoutAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        init();

        layoutDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                startActivity(intent);
            }
        });

//        layoutWithdrawal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        layoutLoans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
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

    private void init(){

        layoutAccounts = findViewById(R.id.accounts_layout);
        layoutCreateAccount = findViewById(R.id.create_account_layout);
        layoutLoans = findViewById(R.id.loans_layout);
        layoutWithdrawal = findViewById(R.id.withdrawal_layout);
        layoutDeposit = findViewById(R.id.deposit_layout);
        layoutTransactions = findViewById(R.id.transactions_layout);

    }

}
