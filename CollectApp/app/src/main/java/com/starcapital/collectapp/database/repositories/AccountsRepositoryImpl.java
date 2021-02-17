package com.starcapital.collectapp.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.starcapital.collectapp.database.AccountsDao;
import com.starcapital.collectapp.database.SaverightDatabase;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

public class AccountsRepositoryImpl implements AccountsRepository {

    private final AccountsDao accountsDao;

    public AccountsRepositoryImpl(Application application) {
        SaverightDatabase db = SaverightDatabase.getDatabase(application);
        accountsDao = db.accountsDao();
    }

    @Override
    public void saveCardTypes(List<CardType> cardTypes) {
        accountsDao.saveCardTypes(cardTypes);
    }

    @Override
    public LiveData<List<CardType>> getCardTypes() {
        return accountsDao.getCardTypes();
    }

    @Override
    public void deleteCardType(CardType cardType) {
        accountsDao.deleteCardType(cardType);
    }
}
