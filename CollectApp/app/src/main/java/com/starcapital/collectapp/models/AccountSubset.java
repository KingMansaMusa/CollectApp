package com.starcapital.collectapp.models;

import java.io.Serializable;

public class AccountSubset implements Serializable {

    private String accountNumber;
    private String accountName;
    private String accountContact;

    public AccountSubset() {
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

    public String getAccountContact() {
        return accountContact;
    }

    public void setAccountContact(String accountContact) {
        this.accountContact = accountContact;
    }
}
