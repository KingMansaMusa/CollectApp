package com.starcapital.collectapp.services;

import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIInterface {

    @GET("/banking/api/v1/cards")
    Call<List<CardType>> getCardTypes();

    @GET("/banking/api/v1/branches")
    Call<List<Branch>> getBranches();
}
