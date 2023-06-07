package com.bank.entities;


import com.bank.repositories.OperacaoBancaria;

public class ContaPoupanca extends Conta implements OperacaoBancaria {
    private static final Double TAX = 0.005;

    public ContaPoupanca() {
    }

    public ContaPoupanca(Pessoa holder, Double balance) {
        super(holder, balance);
    }


    @Override
    public void deposit(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Favor colocar um valor positivo");
        }
        Double interest = amount * (1 + TAX);
        setBalance(getBalance() + interest);
    }

    @Override
    public void withdraw(Double amount) {
        if (amount > getBalance() || amount < 0.0) {
            throw new IllegalArgumentException("Erro ao realizar o saque.");
        }

        setBalance(getBalance() - amount);
    }
}
