package com.bank;

import com.bank.entities.CheckingAccount;


public class Main {
    public static void main(String[] args) {

        CheckingAccount acc = new CheckingAccount("Gustavo", 500.0);
        acc.withdraw(200.0);
        acc.withdraw(300.0);
        acc.withdraw(100.0);
        acc.withdraw(400.0);

        System.out.println(acc);
    }
}