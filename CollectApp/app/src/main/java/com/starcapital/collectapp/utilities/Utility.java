package com.starcapital.collectapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;

import com.starcapital.collectapp.R;

import java.io.ByteArrayOutputStream;

public class Utility {

    private Context context;

    public Utility(Context context) {
        this.context = context;
    }

    public void setAccount(Activity activity, String acc_number, int transaction){

        EditText editTextAccNum = activity.findViewById(R.id.transaction_acc_number);
        EditText editTextAccName = activity.findViewById(R.id.transaction_acc_name);
        EditText editTextTransType = activity.findViewById(R.id.transaction_trans_type);
        EditText editTextOfficer = activity.findViewById(R.id.transaction_officer_name);
        EditText editTextBranch = activity.findViewById(R.id.transaction_branch);

        switch (transaction){
            case 1:
                editTextTransType.setText(context.getString(R.string.deposit));
                break;
            case 2:
                editTextTransType.setText(context.getString(R.string.withdrawal));
                break;
            case 3:
                editTextTransType.setText(context.getString(R.string.loan));
                break;
        }

        editTextAccNum.setText(acc_number);
        editTextAccName.setText(acc_number);
        editTextOfficer.setText(acc_number);
        editTextBranch.setText(acc_number);

    }

    public boolean isDeviceSupportCamera() {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}
