package com.bank.entities;

import com.bank.repositories.OperacaoBancaria;

public class ContaCorrente extends Conta implements OperacaoBancaria {
    private static final Double OVERDRAFT_FIXED_VALUE = 500.0;

    public ContaCorrente() {
    }

    public ContaCorrente(Pessoa holder, Double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(Double bound) {
        double amountToBeSubtract = getBalance() - bound;

        if(amountToBeSubtract < -OVERDRAFT_FIXED_VALUE) {
            throw new IllegalArgumentException("Você não tem créditos para fazer esse saque. Ligue para a agência e negocie!");
        }

        setBalance(getBalance() - bound);
    }

    @Override
    public void deposit(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Favor, colocar apenas valores positivos");
        }

        setBalance(getBalance() + amount);
    }
}


