package com.starcapital.collectapp.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.adapters.AccountsRecyclerAdapter;
import com.starcapital.collectapp.adapters.TransactionsRecyclerAdapter;
import com.starcapital.collectapp.models.Account;
import com.starcapital.collectapp.models.Transaction;
import com.starcapital.collectapp.utilities.DialogUtility;

import java.util.ArrayList;

public class AllTransactionsActivity extends AppCompatActivity {

    RecyclerView recyclerViewTransactions;
    ArrayList transactions = new ArrayList<Transaction>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter transactionsListAdapter;
    Context context;
    DialogUtility dialogUtility;

    Transaction transaction1 = new Transaction();
    Transaction transaction2 = new Transaction();
    Transaction transaction3 = new Transaction();
    Transaction transaction4 = new Transaction();
    Transaction transaction5 = new Transaction();
    Transaction transaction6 = new Transaction();
    Transaction transaction7 = new Transaction();
    Transaction transaction8 = new Transaction();
    Transaction transaction9 = new Transaction();
    Transaction transaction10 = new Transaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        init();
    }

    private void init(){
        transaction1.setAccountName("John Doe");
        transaction1.setAccountNumber("123456");
        transaction1.setAmount(120.50);

        transaction2.setAccountName("John Doe");
        transaction2.setAccountNumber("123456");
        transaction2.setAmount(120.50);

        transaction3.setAccountName("John Doe");
        transaction3.setAccountNumber("123456");
        transaction3.setAmount(120.50);

        transaction4.setAccountName("John Doe");
        transaction4.setAccountNumber("123456");
        transaction4.setAmount(120.50);

        transaction4.setAccountName("John Doe");
        transaction4.setAccountNumber("123456");
        transaction4.setAmount(120.50);

        transaction5.setAccountName("John Doe");
        transaction5.setAccountNumber("123456");
        transaction5.setAmount(120.50);

        transaction6.setAccountName("John Doe");
        transaction6.setAccountNumber("123456");
        transaction6.setAmount(120.50);

        transaction7.setAccountName("John Doe");
        transaction7.setAccountNumber("123456");
        transaction7.setAmount(120.50);

        transaction8.setAccountName("John Doe");
        transaction8.setAccountNumber("123456");
        transaction8.setAmount(120.50);

        transaction9.setAccountName("John Doe");
        transaction9.setAccountNumber("123456");
        transaction9.setAmount(120.50);

        transaction10.setAccountName("John Doe");
        transaction10.setAccountNumber("123456");
        transaction10.setAmount(120.50);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
        transactions.add(transaction7);
        transactions.add(transaction8);
        transactions.add(transaction9);
        transactions.add(transaction10);

        context = AllTransactionsActivity.this;
        recyclerViewTransactions = findViewById(R.id.transactions_recycler_view);
        recyclerViewTransactions.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerViewTransactions.setLayoutManager(layoutManager);
        transactionsListAdapter = new TransactionsRecyclerAdapter(transactions);
        recyclerViewTransactions.setAdapter(transactionsListAdapter);

        dialogUtility = new DialogUtility(context);
    }
}
