package com.starcapital.collectapp.activities;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.utilities.DialogUtility;

public class AccountDetailsActivity extends AppCompatActivity {
    ImageView imageViewAccPhoto, imageViewAccSignature;
    EditText editTextAccountNumber, editTextAccountName, editTextContact, editTextIDNumber, editTextOfficer;
    Spinner spinnerBranch, spinnerIDType;
    Button buttonCancel, buttonEdit;
    Context context;
    DialogUtility dialogUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        init();

        final ArrayAdapter<String> idTypeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.id_types));
        spinnerIDType.setAdapter(idTypeAdapter);

        final ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.branches));
        spinnerBranch.setAdapter(branchAdapter);
    }

    private void init(){
        context = AccountDetailsActivity.this;
        dialogUtility = new DialogUtility(context);
        imageViewAccPhoto = findViewById(R.id.account_details_account_photo);
        imageViewAccSignature = findViewById(R.id.account_details_account_signature);
        editTextAccountNumber = findViewById(R.id.account_details_account_number);
        editTextAccountName = findViewById(R.id.account_details_account_name);
        editTextContact = findViewById(R.id.account_details_contact);
        spinnerIDType = findViewById(R.id.account_details_id_type);
        editTextIDNumber = findViewById(R.id.account_details_id_number);
        editTextOfficer = findViewById(R.id.account_details_officer);
        spinnerBranch = findViewById(R.id.account_details_branch);

        buttonCancel = findViewById(R.id.account_details_cancel_button);
        buttonEdit = findViewById(R.id.account_details_edit_button);

    }

}
