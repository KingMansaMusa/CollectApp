package com.starcapital.collectapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.models.Account;
import com.starcapital.collectapp.utilities.CaptureSignature;
import com.starcapital.collectapp.utilities.DialogUtility;
import com.starcapital.collectapp.utilities.Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.starcapital.collectapp.activities.TransactionActivity.SIGNATURE_ACTIVITY;

public class CreateAccountActivity extends AppCompatActivity {

    ImageView imageViewAccPhoto, imageViewAccSignature;
    EditText editTextAccNum, editTextAccName, editTextContact, editTextIDNum, editTextOfficer;
    Spinner spinnerIDType, spinnerBranch;
    Button buttonCancel, buttonSubmit;

    // Activity request codes
    private static final int CAMERA_REQUEST = 1888;

    public static final int MEDIA_TYPE_IMAGE = 1;


    // directory name to store captured images
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    private Uri fileUri;
    Utility utility;
    DialogUtility dialogUtility;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        init();

        imageViewAccPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!utility.isDeviceSupportCamera()) {
                    Toast.makeText(context, "Camera not supported on this device", Toast.LENGTH_SHORT).show();
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        imageViewAccSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CaptureSignature.class);
                startActivityForResult(intent, SIGNATURE_ACTIVITY);
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Toast.makeText(context, "Account Successfully Created ", Toast.LENGTH_SHORT).show();
                    Account account = submit();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final ArrayAdapter<String> idTypeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.id_types));
        spinnerIDType.setAdapter(idTypeAdapter);

        final ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.branches));
        spinnerBranch.setAdapter(branchAdapter);

        editTextAccName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextAccName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextContact.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextIDNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTextIDNum.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void init() {
        imageViewAccPhoto = findViewById(R.id.create_account_image);
        imageViewAccSignature = findViewById(R.id.create_account_signature);
        editTextAccNum = findViewById(R.id.create_account_number);
        editTextAccName = findViewById(R.id.create_account_name);
        editTextContact = findViewById(R.id.create_account_contact);
        editTextIDNum = findViewById(R.id.create_account_id_number);
        editTextOfficer = findViewById(R.id.create_account_officer);
        spinnerBranch = findViewById(R.id.create_account_branch);
        spinnerIDType = findViewById(R.id.create_account_id_type);
        buttonCancel = findViewById(R.id.create_account_cancel_button);
        buttonSubmit = findViewById(R.id.create_account_submit_button);
        context = CreateAccountActivity.this;
        utility = new Utility(context);
        dialogUtility = new DialogUtility(context);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageViewAccPhoto.setImageBitmap(photo);
            imageViewAccPhoto.setTag("new");
        } else if (requestCode == SIGNATURE_ACTIVITY && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String status = bundle.getString("status");
            if (status.equalsIgnoreCase("done")) {
                Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                toast.show();
                byte[] sign = bundle.getByteArray("encodedImage");
                Log.d("IMAGE", sign.toString());
                Log.d("Bundle Breakdown", "  " + bundle.toString());
                imageViewAccSignature.setImageBitmap(utility.getImage(sign));
                imageViewAccSignature.setTag("new");
            }
        }
    }

    private Account submit() {
        Account account = new Account();
        account.setAccountName(editTextAccName.getText().toString());
        account.setContact(editTextContact.getText().toString());
        account.setIdType(spinnerIDType.getSelectedItem().toString());
        account.setIdNumber(editTextIDNum.getText().toString());
        account.setOfficer(editTextOfficer.getText().toString());
        account.setBranch(spinnerBranch.getSelectedItem().toString());
        account.setAccountPhoto(utility.getBytes(((BitmapDrawable) imageViewAccPhoto.getDrawable()).getBitmap()));
        account.setAccountSignature(utility.getBytes(((BitmapDrawable) imageViewAccSignature.getDrawable()).getBitmap()));

        return account;
    }

    private boolean validate() {
        boolean state = true;
        if (imageViewAccPhoto.getTag().toString().equals("noset")) {
            Toast.makeText(context, "Please add the Account Photo", Toast.LENGTH_SHORT).show();
            state = false;
        }

        if (imageViewAccSignature.getTag().toString().equals("noset")) {
            Toast.makeText(context, "Please add the Account Signature", Toast.LENGTH_SHORT).show();
            state = false;
        }

        if (editTextAccName.getText().toString().equals("")) {
            editTextAccName.setError("Please enter the Account Name");
            state = false;
        }

        if (editTextContact.getText().toString().equals("")) {
            editTextContact.setError("Please enter the Contact of the Account");
            state = false;
        }

        if (editTextIDNum.getText().toString().equals("")) {
            editTextIDNum.setError("Please enter the ID Number");
            state = false;
        }


        return state;
    }

}
