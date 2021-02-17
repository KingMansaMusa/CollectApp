package com.starcapital.collectapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.starcapital.collectapp.R;

import net.openid.appauth.AuthState;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;

import static android.content.Context.MODE_PRIVATE;

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

    public boolean getLogout() {
        boolean isLogout;
        SharedPreferences sharedPreferences = context.getSharedPreferences("Credentials", MODE_PRIVATE);
        isLogout = sharedPreferences.getBoolean("isLogout", false);
        return isLogout;
    }

    public void setLogout(boolean isLogout) {
        SharedPreferences sp = context.getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putBoolean("isLogout", isLogout);
        Ed.apply();
    }

    //This method save the AuthState in the Shared Preference so that it can be accessed throughout the whole application
    public void writeAuthState(@NonNull AuthState state) {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", MODE_PRIVATE);
        authPrefs.edit()
                .putString("stateJson", state.jsonSerializeString())
                .apply();
    }

    //This method reads the saved AuthState from the Shared Prefernce so that it can be used for network requests
    @NonNull
    public AuthState readAuthState() throws JSONException {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", MODE_PRIVATE);
        String stateJson = authPrefs.getString("stateJson", null);
        if (stateJson != null) {
            return AuthState.jsonDeserialize(stateJson);
        } else {
            return new AuthState();
        }
    }
}
