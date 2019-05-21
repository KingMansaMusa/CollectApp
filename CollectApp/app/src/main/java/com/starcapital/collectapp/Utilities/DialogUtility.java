package com.starcapital.collectapp.Utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.starcapital.collectapp.Activities.MainActivity;
import com.starcapital.collectapp.Activities.TransactionActivity;
import com.starcapital.collectapp.R;

public class DialogUtility {

    private Context context;
    private Utility utility;

    public DialogUtility(Context context) {
        this.context = context;
        this.utility = new Utility(context);
    }

    public Dialog showProcessDialog() {

        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_dialog);

        ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(R.id.progress_processing);

        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public Dialog showAccountSearchDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.account_search_dialog);


        final EditText editTextAccountNumber = dialog.findViewById(R.id.account_search_acc_number);
        Button buttonCancel = dialog.findViewById(R.id.account_search_button_cancel);
        Button buttonSearch = dialog.findViewById(R.id.account_search_button_search);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                dialog.dismiss();
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String acc_number = editTextAccountNumber.getText().toString();

                if (acc_number.isEmpty()) {
                    editTextAccountNumber.setError("Please enter an Account Number");

                } else {
                    dialog.dismiss();
                    utility.setAccount((Activity) context, acc_number);
                }

            }
        });

        editTextAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextAccountNumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }


}
