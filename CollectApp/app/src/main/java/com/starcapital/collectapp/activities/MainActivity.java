package com.starcapital.collectapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.models.Transaction;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutDeposit, layoutLoans, layoutWithdrawal, layoutCreateAccount, layoutTransactions, layoutAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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

    private void init(){

        layoutAccounts = findViewById(R.id.accounts_layout);
        layoutCreateAccount = findViewById(R.id.create_account_layout);
        layoutLoans = findViewById(R.id.loans_layout);
        layoutWithdrawal = findViewById(R.id.withdrawal_layout);
        layoutDeposit = findViewById(R.id.deposit_layout);
        layoutTransactions = findViewById(R.id.transactions_layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
        }

        return true;
    }
}
