package com.starcapital.collectapp.services;

import com.google.gson.annotations.SerializedName;
import com.starcapital.collectapp.models.Account;
import com.starcapital.collectapp.models.AccountSubset;
import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/saveright/api/v1/cards")
    Call<List<CardType>> getCardTypes();

    @GET("/saveright/api/v1/branches")
    Call<List<Branch>> getBranches();


    @GET("/saveright/api/v1/accounts/search")
    Call<List<AccountSubset>> getAccounts(@Query("q") String name, @Query("sort") String sort, @Query("size") int size, @Query("agent") String agent);
}
