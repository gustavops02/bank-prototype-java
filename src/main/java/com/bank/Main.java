package com.bank;


import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.Pessoa;
import com.bank.servicos.ServicoContas;


public class Main {
    public static void main(String[] args) {

        Conta conta = new ContaCorrente(new Pessoa("17412379712", "Gustavo Paulo"), 0.0);

        ServicoContas.deletarConta(1);

    }
}