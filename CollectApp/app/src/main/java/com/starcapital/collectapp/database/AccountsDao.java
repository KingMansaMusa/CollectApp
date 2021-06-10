package com.starcapital.collectapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.starcapital.collectapp.models.Branch;
import com.starcapital.collectapp.models.CardType;

import java.util.List;

@Dao
public interface AccountsDao {

    @Query("SELECT * FROM card_type")
    List<CardType> getCardTypes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCardTypes(List<CardType> cardTypes);

    @Delete
    void deleteCardType(CardType cardType);

    @Query("SELECT * FROM branch")
    List<Branch> getBranches();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBranches(List<Branch> branches);

    @Delete
    void deleteBranch(Branch branch);

}
