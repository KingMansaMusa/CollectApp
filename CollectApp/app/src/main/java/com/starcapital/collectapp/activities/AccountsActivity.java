package com.starcapital.collectapp.activities;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.adapters.AccountsRecyclerAdapter;
import com.starcapital.collectapp.models.AccountSubset;
import com.starcapital.collectapp.utilities.DialogUtility;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends AppCompatActivity {

    DialogUtility dialogUtility;
    RecyclerView recyclerViewAccounts;
    ArrayList accounts = new ArrayList<AccountSubset>();
    RecyclerView.LayoutManager layoutManager;
    AccountsRecyclerAdapter accountsListAdapter;
    Context context;

    AccountSubset account1 = new AccountSubset();
    AccountSubset account2 = new AccountSubset();
    AccountSubset account3 = new AccountSubset();
    AccountSubset account4 = new AccountSubset();
    AccountSubset account5 = new AccountSubset();
    AccountSubset account6 = new AccountSubset();
    AccountSubset account7 = new AccountSubset();
    AccountSubset account8 = new AccountSubset();
    AccountSubset account9 = new AccountSubset();
    AccountSubset account10 = new AccountSubset();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dialogUtility = new DialogUtility(AccountsActivity.this);
        dialogUtility.showAccountsDialog().show();

        init();
    }

    private void init() {

        account1.setAccountNumber("123456");
        account1.setAccountName("John Doe");
        account1.setAccountContact("0244444444");

        account2.setAccountNumber("123456");
        account2.setAccountName("John Doe");
        account2.setAccountContact("0244444444");

        account3.setAccountNumber("123456");
        account3.setAccountName("John Doe");
        account3.setAccountContact("0244444444");

        account4.setAccountNumber("123456");
        account4.setAccountName("John Doe");
        account4.setAccountContact("0244444444");

        account5.setAccountNumber("123456");
        account5.setAccountName("John Doe");
        account5.setAccountContact("0244444444");

        account6.setAccountNumber("123456");
        account6.setAccountName("John Doe");
        account6.setAccountContact("0244444444");

        account7.setAccountNumber("123456");
        account7.setAccountName("John Doe");
        account7.setAccountContact("0244444444");

        account8.setAccountNumber("123456");
        account8.setAccountName("John Doe");
        account8.setAccountContact("0244444444");

        account9.setAccountNumber("123456");
        account9.setAccountName("John Doe");
        account9.setAccountContact("0244444444");

        account10.setAccountNumber("123456");
        account10.setAccountName("John Doe");
        account10.setAccountContact("0244444444");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);
        accounts.add(account6);
        accounts.add(account7);
        accounts.add(account8);
        accounts.add(account9);
        accounts.add(account10);

        context = AccountsActivity.this;
        recyclerViewAccounts = findViewById(R.id.accounts_recycler_view);
        recyclerViewAccounts.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerViewAccounts.setLayoutManager(layoutManager);
        accountsListAdapter = new AccountsRecyclerAdapter(accounts);
        recyclerViewAccounts.setAdapter(accountsListAdapter);

    }

    public void addData(List<AccountSubset> accounts) {
        accountsListAdapter = new AccountsRecyclerAdapter(accounts);
        recyclerViewAccounts.setAdapter(accountsListAdapter);
    }

}
