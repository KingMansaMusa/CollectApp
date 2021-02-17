package com.starcapital.collectapp.utilities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.starcapital.collectapp.R;
import com.starcapital.collectapp.activities.MainActivity;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

import java.util.Map;

public class LoginHelper {
    Context context;
    PendingIntent successPendingIntent, cancelPendingIntent;
    final static String TAG = "AuthServiceConfigLog";
    AuthState authState;
    Utility utility;
    boolean fromLogin;
    Resources res;

    public LoginHelper(Context context, PendingIntent successPendingIntent, PendingIntent cancelPendingIntent, boolean fromLogin) {
        this.context = context;
        this.successPendingIntent = successPendingIntent;
        this.cancelPendingIntent = cancelPendingIntent;
        utility = new Utility(context);
        this.fromLogin = fromLogin;
        this.res = context.getResources();

    }

    public void authenticate() {
        //This call is to hit the login of the IDP.
        //Uri,parse()takes the Uri which will provide the login page
        //AuthState is the session of the user and takes the configuration created
        //The AutRequestBuilder builds the request to the IDP for the login
        //It takes the Client ID and the REDIRECT URI which id the package of the app
        //The redirect URI should be set in the Client in the IDP
        //The Code only specifies how send the response value types back to the mobile
        AuthorizationServiceConfiguration.fetchFromIssuer(
                Uri.parse(res.getString(R.string.login_idp_endpoint)),//The Login URL for the IDP
                (serviceConfiguration, ex) -> {
                    if (ex != null) {
                        Log.e(TAG, "failed to fetch configuration");
                        Toast.makeText(context, "Login in failed. Please check your internet", Toast.LENGTH_SHORT).show();
                        if (fromLogin){
                            context.startActivity(new Intent(context, MainActivity.class));
                        }
                        return;
                    }

                    assert serviceConfiguration != null;
                    authState = new AuthState(serviceConfiguration);
                    AuthorizationRequest.Builder authRequestBuilder =
                            new AuthorizationRequest.Builder(
                                    serviceConfiguration, // the authorization service configuration
                                    res.getString(R.string.login_client_id), // the client ID, typically pre-registered and static
                                    ResponseTypeValues.CODE, // the response_type value: we want a code
                                    Uri.parse(res.getString(R.string.login_redirect_uri))); // the redirect URI to which the auth response is sent
                    if (utility.getLogout()) {
                        authRequestBuilder.setPrompt("login");
                    }
                    doAuthorization(authRequestBuilder.build());
                });
    }

    //This method makes the actual call to the IDP
    //It takes the AuthRequest that will be returned by the builder and performs a request to the IDP via a browser
    //the performAuthorization() method takes the authRequest, the pendingIntent for success and the pendingIntent on cancel
    //The the authService is disposed off after
    private void doAuthorization(AuthorizationRequest authRequest) {
        AuthorizationService authService = new AuthorizationService(context);
        authService.performAuthorizationRequest(authRequest, successPendingIntent, cancelPendingIntent);
        authService.dispose();
    }

    //This method is strictly for logging purpose.
    //It logs the response or exception from the browser
    public void showData(AuthorizationResponse resp, AuthorizationException ex) {
        if (ex == null) {
            Map<String, String> userName = resp.additionalParameters;
            Log.d(TAG, userName.toString());
            String accessToken = resp.accessToken;
            Log.d("ACCESS TOKEN", accessToken + "");
            String identityToken = resp.idToken;
            Log.d("IDENTITY TOKEN", identityToken + "");
        } else {
            Toast.makeText(context, "User not Authenticated", Toast.LENGTH_SHORT).show();
        }

    }

}
