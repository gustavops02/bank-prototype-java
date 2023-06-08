package com.bank.entidades;

import com.bank.repositorios.OperacaoBancaria;

public class ContaCorrente extends Conta implements OperacaoBancaria {
    private static final Double TAXA_CHEQUE_ESPECIAL = 500.0;

    public ContaCorrente() {
    }

    public ContaCorrente(Pessoa titular, Double saldo) {
        super(titular, saldo);
    }

    @Override
    public void saque(Double valor) {
        double valorParaSubtrair = getSaldo() - valor;

        if(valorParaSubtrair < -TAXA_CHEQUE_ESPECIAL) {
            throw new IllegalArgumentException("Você não tem créditos para fazer esse saque. Ligue para a agência e negocie!");
        }

        setSaldo(getSaldo() - valor);
    }

    @Override
    public void deposito(Double valor) {
        if (valor < 0.0) {
            throw new IllegalArgumentException("Favor, colocar apenas valores positivos");
        }

        setSaldo(getSaldo() + valor);
    }
}


