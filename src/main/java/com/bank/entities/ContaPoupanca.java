package com.bank.entities;


public class ContaPoupanca extends Conta {
    private static final Double TAX = 0.005;

    public ContaPoupanca() {
    }

    public ContaPoupanca(Pessoa holder, Double balance) {
        super(holder, balance);
    }
}
