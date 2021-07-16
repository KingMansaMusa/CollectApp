package com.starcapital.collectapp.services;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starcapital.collectapp.R;
import com.starcapital.collectapp.utilities.Utility;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationService;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit getClient(Context context) throws JSONException {

        Utility utility = new Utility(context);
        AuthorizationService authorizationService = new AuthorizationService(context);
        AuthState authState = utility.readAuthState();
        Resources res = context.getResources();

        //This logging interceptor logs the whole process of the call . Bring the entire response returned as a sting in the logs
        //The logging is set to the body level to show the body of the response
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //The client interceptor monitors, rewrite and retry calls. The logging interceptor is added to the client interceptor
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        authState.performActionWithFreshTokens(authorizationService, (accessToken, idToken, ex) -> {

            if (ex != null) {
                // negotiation for fresh tokens failed, check ex for more details
                Log.w("EXCEPTION TOKEN", "" + ex);
                return;
            }
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header(res.getString(R.string.authorization_header),
                                res.getString(R.string.bearer_token, accessToken)); // <-- this is the important line
                assert accessToken != null;
                Log.i("TOKEN", accessToken);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
        });

        OkHttpClient client = httpClient
                .addInterceptor(interceptor)
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder()
                .client(client)
                //The API url without the individual endpoints
//                .baseUrl("http://192.168.250.209:8001")
//                .baseUrl("http://foo.psyphy.com/psyphy/")
                .baseUrl(res.getString(R.string.base_url))
                //The Gson converter converts the JSON response to Java object and Java object to JSON
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        .build();
    }
}
