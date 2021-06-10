package com.starcapital.collectapp.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.starcapital.collectapp.database.repositories.AccountsRepository;
import com.starcapital.collectapp.database.repositories.AccountsRepositoryImpl;
import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

public class AccountsViewModel extends AndroidViewModel {

    private AccountsRepositoryImpl accountsRepository;

    public AccountsViewModel(@NonNull Application application) {
        super(application);
        this.accountsRepository = new AccountsRepositoryImpl(application);
    }


    public List<CardType> getCardTypes(){
        return accountsRepository.getCardTypes();
    }

    public void saveCards(List<CardType> cardTypes){
        accountsRepository.saveCardTypes(cardTypes);
    }

    public List<Branch> getBranches(){
        return accountsRepository.getBranches();
    }

    public void saveBranches(List<Branch> branches){
        accountsRepository.saveBranches(branches);
    }

}
