package com.starcapital.collectapp.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.starcapital.collectapp.activities.MainActivity;
import com.starcapital.collectapp.R;
import com.starcapital.collectapp.services.NetworkCalls;

import org.json.JSONException;

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

    public Dialog showAccountSearchDialog(final int transaction) {
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
                    utility.setAccount((Activity) context, acc_number, transaction);
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

    public Dialog showAccountsDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.accounts_dialog);

        NetworkCalls networkCalls = new NetworkCalls(context);
        final boolean[] accountNumber = {true};
        final Spinner spinnerSearchType = dialog.findViewById(R.id.accounts_search_type);
        final TextView textViewParameter = dialog.findViewById(R.id.accounts_parameter_text);
        final EditText editTextParameter = dialog.findViewById(R.id.accounts_parameter);
        Button buttonCancel = dialog.findViewById(R.id.accounts_button_cancel);
        Button buttonSearch = dialog.findViewById(R.id.accounts_button_search);

        String[] searchTypes = context.getResources().getStringArray(R.array.account_search_types);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, searchTypes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchType.setAdapter(adapter1);


        spinnerSearchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerSearchType.getSelectedItem().toString().equals(context.getResources().getString(R.string.account_number))) {
                    textViewParameter.setText(R.string.account_number);
                    accountNumber[0] = true;
                    editTextParameter.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                } else {
                    textViewParameter.setText(R.string.account_name);
                    editTextParameter.setRawInputType(InputType.TYPE_CLASS_TEXT);
                    accountNumber[0] = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                String search = editTextParameter.getText().toString();
                String acc_number = "accountNumber";
                String acc_name = "accountName";
                String agent = "mobile";
                int size = 100;

                if (search.isEmpty()) {
                    editTextParameter.setError("Please enter a Parameter to search");

                } else {
                    try {
                        if (accountNumber[0]) {
                            networkCalls.getAccounts(search, acc_number, size, agent);
                        } else {
                            networkCalls.getAccounts(search, acc_name, size, agent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }

            }
        });

        editTextParameter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextParameter.setError(null);
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
