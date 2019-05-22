package com.starcapital.collectapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.Utilities.CaptureSignature;
import com.starcapital.collectapp.Utilities.DialogUtility;

public class TransactionActivity extends AppCompatActivity {

    EditText editTextAccNum, editTextAccName, editTextTransType, editTextAmount, editTextOfficer, editTextBranch;
    Button buttonCancel, buttonSubmit;
    DialogUtility dialogUtility;
    public static final int SIGNATURE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dialogUtility = new DialogUtility(TransactionActivity.this);
        init();

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
                    }
                }
                break;
        }

    }
}
