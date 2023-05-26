package com.bank.entities;


public class SavingsAccount extends Account {
    private static final Double TAX = 0.005;

    public SavingsAccount() {
    }

    public SavingsAccount(String holder, Double balance) {
        super(holder, balance);
    }
}
