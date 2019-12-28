package com.starcapital.collectapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.utilities.CaptureSignature;
import com.starcapital.collectapp.utilities.DialogUtility;

public class TransactionActivity extends AppCompatActivity {

    EditText editTextAccNum, editTextAccName, editTextTransType, editTextAmount, editTextOfficer, editTextBranch;
    Button buttonCancel, buttonSubmit;
    DialogUtility dialogUtility;
    public static final int SIGNATURE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dialogUtility = new DialogUtility(TransactionActivity.this);
        init();

        Intent intent = getIntent();
        int transaction = (int) intent.getSerializableExtra("transaction");

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CaptureSignature.class);
                startActivityForResult(intent, SIGNATURE_ACTIVITY);
            }
        });

        dialogUtility.showAccountSearchDialog(transaction).show();

    }

    private void init() {

        editTextAccNum = findViewById(R.id.transaction_acc_number);
        editTextAccName = findViewById(R.id.transaction_acc_name);
        editTextTransType = findViewById(R.id.transaction_trans_type);
        editTextAmount = findViewById(R.id.transaction_amount);
        editTextOfficer = findViewById(R.id.transaction_officer_name);
        editTextBranch = findViewById(R.id.transaction_branch);

        buttonCancel = findViewById(R.id.transaction_button_cancel);
        buttonSubmit = findViewById(R.id.transaction_button_submit);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    if(status.equalsIgnoreCase("done")){
                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.show();
                        byte[] sign = bundle.getByteArray("encodedImage");
                        Log.d("Bundle Breakdown", "  "+bundle.toString());

                        Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                        this.startActivity(intent);
                        TransactionActivity.this.finish();
                    }
                }
                break;
        }

    }
}
