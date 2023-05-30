package com.bank;

import com.bank.entities.ContaCorrente;
import com.bank.entities.ContaPoupanca;
import com.bank.entities.Pessoa;


public class Main {
    public static void main(String[] args) {

        ContaPoupanca acc = new ContaPoupanca(new Pessoa("17412379712", "Gustavo"), 500.0);
        ContaCorrente accCorrente = new ContaCorrente(new Pessoa("17412379712", "Gustavo"), 500.0);

        System.out.println(acc);
        System.out.println(accCorrente);
    }
}