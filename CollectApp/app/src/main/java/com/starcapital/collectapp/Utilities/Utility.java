package com.starcapital.collectapp.Utilities;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import com.starcapital.collectapp.R;

public class Utility {

    private Context context;

    public Utility(Context context) {
        this.context = context;
    }

    public void setAccount(Activity activity, String acc_number){

        EditText editTextAccNum = activity.findViewById(R.id.transaction_acc_number);
        EditText editTextAccName = activity.findViewById(R.id.transaction_acc_name);
        EditText editTextTransType = activity.findViewById(R.id.transaction_trans_type);
        EditText editTextAmount = activity.findViewById(R.id.transaction_amount);
        EditText editTextOfficer = activity.findViewById(R.id.transaction_officer_name);
        EditText editTextBranch = activity.findViewById(R.id.transaction_branch);

        editTextAccNum.setText(acc_number);
        editTextAccName.setText(acc_number);
        editTextTransType.setText(acc_number);
        editTextAmount.setText(acc_number);
        editTextOfficer.setText(acc_number);
        editTextBranch.setText(acc_number);

    }

}
