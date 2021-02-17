package com.starcapital.collectapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "card_type")
public class CardType implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("cardTypeId")
    private UUID id;

    @SerializedName("cardTypeName")
    private String name;

    public CardType() {
    }

    @Override
    public String toString() {
        return name;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
