package com.starcapital.collectapp.services;

import com.starcapital.collectapp.models.CardType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/saveright/api/v1/cards")
    Call<List<CardType>> getCardTypes();
}
