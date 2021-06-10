package com.starcapital.collectapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "branch")
public class Branch implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("branchId")
    private UUID id;
    @ColumnInfo(name = "name")
    @SerializedName("branchName")
    private String name;
    @ColumnInfo(name = "address")
    @SerializedName("branchAddress")
    private String address;
    @ColumnInfo(name = "state")
    @SerializedName("branchState")
    private String state;

    public Branch() {
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

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
