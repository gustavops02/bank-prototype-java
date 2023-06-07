package com.bank.repositories;

public interface OperacaoBancaria {
    void deposit(Double amount);
    void withdraw(Double amount);
}
