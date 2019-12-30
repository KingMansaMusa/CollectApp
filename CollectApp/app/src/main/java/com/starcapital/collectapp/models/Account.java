package com.starcapital.collectapp.models;

import java.io.Serializable;

public class Account implements Serializable {

    private String accountNumber;
    private String accountName;
    private String contact;
    private String idType;
    private String idNumber;
    private String officer;
    private String branch;
    private byte[] accountSignature;
    private byte[] accountPhoto;

    public Account(){

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public byte[] getAccountSignature() {
        return accountSignature;
    }

    public void setAccountSignature(byte[] accountSignature) {
        this.accountSignature = accountSignature;
    }

    public byte[] getAccountPhoto() {
        return accountPhoto;
    }

    public void setAccountPhoto(byte[] accountPhoto) {
        this.accountPhoto = accountPhoto;
    }
}
