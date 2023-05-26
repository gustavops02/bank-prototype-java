package com.bank.entities;

import java.util.Random;

/**
 * This abstract class contains all attr's and methods that uses in your subclasses.
 */
abstract class Account {
    //TODO: Create a Logger to all withdraws and deposits. Remember - Create a config.WithdrawLog to do this onE.

    private Integer accountNumber;

    /**
     * After I will update this type from Person. Person not exists yet.
     */
    private String holder;
    private Double balance;

    public Account() {
        this.accountNumber = this.createRandomAccountNumber();
    }

    public Account(String holder, Double balance) {
        this.accountNumber = this.createRandomAccountNumber();
        this.holder = holder;
        this.balance = balance;
    }

    private Integer createRandomAccountNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    /*  GETTERS AND SETTERS  */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolder() {
        return holder;
    }


    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account number: " + getAccountNumber() + "\nHolder: " + getHolder() + "\nBalance: " + getBalance();
    }
}
