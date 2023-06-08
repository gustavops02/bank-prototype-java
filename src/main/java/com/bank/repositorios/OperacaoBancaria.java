package com.bank.repositorios;

public interface OperacaoBancaria {
    void deposito(Double valor);
    void saque(Double valor);
}
