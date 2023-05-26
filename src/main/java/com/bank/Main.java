package com.bank;

import com.bank.entities.SavingsAccount;


public class Main {
    public static void main(String[] args) {

        SavingsAccount acc = new SavingsAccount("Gustavo", 500.0);
        acc.withdraw(20.0);

        acc.withdraw(45.0);
    }
}