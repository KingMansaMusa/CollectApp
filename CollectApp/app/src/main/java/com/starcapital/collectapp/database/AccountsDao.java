package com.starcapital.collectapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.starcapital.collectapp.models.CardType;

import java.util.List;

@Dao
public interface AccountsDao {

    @Query("SELECT * FROM card_type")
    LiveData<List<CardType>> getCardTypes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCardTypes(List<CardType> cardTypes);

    @Delete
    void deleteCardType(CardType cardType);

}
