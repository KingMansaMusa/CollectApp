package com.starcapital.collectapp.database.repositories;

import androidx.lifecycle.LiveData;

import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

public interface AccountsRepository {

    void saveCardTypes(List<CardType> cardTypes);

    List<CardType> getCardTypes();

    void deleteCardType(CardType cardType);

    void saveBranches(List<Branch> branches);

    List<Branch> getBranches();

    void deleteBranch(Branch branch);

}
