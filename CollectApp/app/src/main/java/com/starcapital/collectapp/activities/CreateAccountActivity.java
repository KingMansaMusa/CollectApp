package com.starcapital.collectapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.starcapital.collectapp.utilities.Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CreateAccountActivity extends AppCompatActivity {

    ImageView imageViewAccPhoto, imageViewAccSignature;
    EditText editTextAccNum, editTextAccName, editTextContact, editTextIDNum, editTextOfficer;
    Spinner spinnerIDType, spinnerBranch;
    Button buttonCancel, buttonSubmit;

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

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
                if(!utility.isDeviceSupportCamera()){
                    Toast.makeText(context, "Camera not supported on this device", Toast.LENGTH_SHORT).show();
                }
                else{
                    captureImage();
                }
            }
        });

    }

    private void init(){
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



    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;

    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = Uri.fromFile(new File(String.valueOf(getOutputMediaFile(MEDIA_TYPE_IMAGE))));



        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void previewCapturedImage() {
        try {
            // hide video preview
//            videoPreview.setVisibility(View.GONE);

//            imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imageViewAccPhoto.setImageBitmap(bitmap);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }}


}
