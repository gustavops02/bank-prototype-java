package com.bank.entidades;



public class ContaPoupanca extends Conta {
    private static final Double TAXA = 0.005;

    public ContaPoupanca(Pessoa titular, Double saldo) {
        super(titular, saldo);
    }


    @Override
    public void deposito(Double valor) {
        if (valor < 0.0) {
            throw new IllegalArgumentException("Favor colocar um valor positivo");
        }
        Double juros = valor * (1 + TAXA);
        setSaldo(getSaldo() + juros);
    }

    @Override
    public void saque(Double valor) {
        if (valor > getSaldo() || valor < 0.0) {
            throw new IllegalArgumentException("Erro ao realizar o saque.");
        }

        setSaldo(getSaldo() - valor);
    }
}
