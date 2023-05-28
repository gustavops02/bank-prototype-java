package com.bank;

import com.bank.entities.ContaCorrente;
import com.bank.entities.Pessoa;


public class Main {
    public static void main(String[] args) {

        ContaCorrente acc = new ContaCorrente(new Pessoa("17412379712", "Gustavo"), 500.0);
        acc.withdraw(200.0);
        acc.withdraw(300.0);
        acc.withdraw(100.0);
        acc.withdraw(400.0);
        System.out.println(acc);
    }
}