package com.starcapital.collectapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.starcapital.collectapp.R;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
