package com.starcapital.collectapp.database.repositories;

import androidx.lifecycle.LiveData;

import com.starcapital.collectapp.models.CardType;

import java.util.List;

public interface AccountsRepository {

    void saveCardTypes(List<CardType> cardTypes);

    LiveData<List<CardType>> getCardTypes();

    void deleteCardType(CardType cardType);

}
