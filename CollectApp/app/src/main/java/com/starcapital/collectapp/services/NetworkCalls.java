package com.starcapital.collectapp.services;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.starcapital.collectapp.database.viewmodels.AccountsViewModel;
import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;
import com.starcapital.collectapp.utilities.DialogUtility;
import com.starcapital.collectapp.utilities.Utility;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {

    Context context;
    APIInterface apiInterface;
    AccountsViewModel viewModel;
    Utility utility;
    Dialog dialog;

    public NetworkCalls(Context context) {
        this.context = context;
        this.viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(AccountsViewModel.class);
        utility = new Utility(context);
        dialog = new DialogUtility(context).showProcessDialog();
    }

    public void saveCardTypes() throws JSONException {
        dialog.show();
        apiInterface = APIClient.getClient(context).create(APIInterface.class);
        Call<List<CardType>> call = apiInterface.getCardTypes();
        call.enqueue(new Callback<List<CardType>>() {
            @Override
            public void onResponse(Call<List<CardType>> call, Response<List<CardType>> response) {
                if (response.isSuccessful()) {
                    Log.d("GETTING CARDS GOOD--", response.toString());
                    new SaveCards().execute(response.body());
                    dialog.dismiss();
                } else{
                    Log.d("GETTING CARDS BAD--", response.code() + " " + response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<CardType>> call, Throwable t) {
                dialog.dismiss();
                Log.d("GETTING CARDS FAILED---", t.toString());
            }
        });
    }


    public void saveBranches() throws JSONException {
        dialog.show();
        apiInterface = APIClient.getClient(context).create(APIInterface.class);
        Call<List<Branch>> call = apiInterface.getBranches();
        call.enqueue(new Callback<List<Branch>>() {
            @Override
            public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                if (response.isSuccessful()) {
                    Log.d("GETTING CARDS GOOD--", response.toString());
                    new SaveBranches().execute(response.body());
                    dialog.dismiss();
                } else{
                    Log.d("GETTING CARDS BAD--", response.code() + " " + response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Branch>> call, Throwable t) {
                dialog.dismiss();
                Log.d("GETTING CARDS FAILED---", t.toString());
            }
        });
    }

    public class SaveCards extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... objects) {
            List<CardType> cardTypes = (List<CardType>) objects[0];
            viewModel.saveCards(cardTypes);
            Log.d("CARDS INSERTED", "FROM WEB");
            return "Successful";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    public class SaveBranches extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... objects) {
            List<Branch> branches = (List<Branch>) objects[0];
            viewModel.saveBranches(branches);
            Log.d("BRANCHES INSERTED", "FROM WEB");
            return "Successful";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}
