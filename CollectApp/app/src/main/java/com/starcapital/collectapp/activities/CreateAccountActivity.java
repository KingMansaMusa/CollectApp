package com.starcapital.collectapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.utilities.CaptureSignature;
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

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageViewAccPhoto.setImageBitmap(photo);
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

            }
        }
    }


}
