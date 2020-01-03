package com.starcapital.collectapp.models;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String accountNumber;
    private String accountName;
    private String TransactionType;
    private double amount;
    private String officer;
    private String branch;
    private byte[] accountSignature;

    public Transaction(){

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

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
