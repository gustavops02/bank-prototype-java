package com.bank.utilitarios;


import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;
import com.bank.servicos.ServicoContas;

import java.util.Scanner;

public class UsarServico {

    public static void executarCadastro(Scanner sc) {

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        Pessoa pessoa = new Pessoa(cpf, nome);

        DesignConfig.tipoDeConta();
        System.out.print("Escolha uma opção: ");
        String tipo = sc.next().toUpperCase();

        System.out.print("Irá fazer depósito no ato do cadastro?\nSe sim, coloque o valor: ");
        Double saldo = sc.nextDouble();

        if (tipo.equals("CP")) {
            ServicoContas.cadastrarConta(new ContaPoupanca(pessoa, saldo));
        } else if (tipo.equals("CC")) {
            ServicoContas.cadastrarConta(new ContaCorrente(pessoa, saldo));

        } else {
            System.out.println("erro");
        }
    }

    public static void consultaSaldo(Conta conta) {
        System.out.print(conta.getSaldo());

    }

    public static void deposito(Scanner sc, Conta conta) {

        System.out.print("Qual o valor para depósito? ");
        double valor = sc.nextDouble();

        if (conta instanceof ContaCorrente) {
            ServicoContas.deposito(conta, valor);
        } else {
            ServicoContas.deposito(conta, valor);
        }
        System.out.println("Depósito feito!");

    }

    public static void saque(Scanner sc, Conta conta) {
        System.out.print("Qual o valor para o saque? ");
        double val = sc.nextDouble();

        if (conta instanceof ContaCorrente) {
            ServicoContas.saque((ContaCorrente) conta, val);
        } else {
            ServicoContas.saque((ContaPoupanca) conta, val);
        }
    }

    public static void remover(Scanner sc, Conta conta) {
        System.out.print("Tem certeza que deseja remover sua conta? (s/n)");
        String confirma = sc.next().toLowerCase();
        sc.nextLine();

        if (confirma.equals("s")) {
            ServicoContas.deletarConta(conta);
        }
    }
}
