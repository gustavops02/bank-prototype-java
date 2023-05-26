package com.bank.entities;

public class CheckingAccount extends Account {
    private static final Double OVERDRAFT_FIXED_VALUE = 500.0;

    public CheckingAccount() {
    }

    public CheckingAccount(String holder, Double balance) {
        super(holder, balance);
    }
}


