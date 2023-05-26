package com.bank.entities;

public class CheckingAccount extends Account {
    private static final Double OVERDRAFT_FIXED_VALUE = 500.0;

    public CheckingAccount() {
    }

    public CheckingAccount(String holder, Double balance) {
        super(holder, balance);
    }

    public void withdraw(Double bound) {
        double amountToBeSubtract = getBalance() - bound;

        if(amountToBeSubtract < -OVERDRAFT_FIXED_VALUE) {
            throw new IllegalArgumentException("You don't have more credits to do this withdraw.");
        }

        setBalance(getBalance() - bound);

    }
}


