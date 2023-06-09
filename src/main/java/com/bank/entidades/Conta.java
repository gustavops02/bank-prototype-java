package com.bank.entidades;

import java.util.Random;


public abstract class Conta {
    private Integer id;
    private Integer numeroConta;

    private Pessoa titular;
    private Double saldo;


    public Conta(Pessoa titular, Double saldo) {
        this.numeroConta = this.createRandomNumeroConta();
        this.titular = titular;
        this.saldo = saldo;
    }

    private Integer createRandomNumeroConta() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    public abstract void deposito(Double valor);

    public abstract void saque(Double valor);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Numero da conta: " + getNumeroConta() + "\ntitular: " + getTitular() + "\nsaldo: " + getSaldo();
    }
}
