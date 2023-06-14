package com.bank.utilitarios;


import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;
import com.bank.servicos.ServicoContas;

import java.util.Scanner;

public class UsarServico {

    public static Conta executarCadastro(Scanner sc) {

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

        Conta conta = null;

        if (tipo.equals("CP")) {
            conta = new ContaPoupanca(pessoa, saldo);
            ServicoContas.cadastrarConta(conta);
        } else if (tipo.equals("CC")) {
            conta = new ContaCorrente(pessoa, saldo);
            ServicoContas.cadastrarConta(conta);

        } else {
            System.out.println("erro");
        }
        System.out.println("\n\n"+ conta);
        return conta;
    }

    public static void consultaSaldo(Conta conta) {
        System.out.println("Saldo: R$ " + conta.getSaldo());

    }

    public static void deposito(Scanner sc, Conta conta) {

        System.out.print("Qual o valor para depósito? ");
        double valor = sc.nextDouble();

        ServicoContas.deposito(conta, valor);

        System.out.println("Depósito feito!\nSaldo atual: R$ " + conta.getSaldo());

    }

    public static void saque(Scanner sc, Conta conta) {
        System.out.print("Qual o valor para o saque? ");
        double val = sc.nextDouble();

        ServicoContas.saque(conta, val);
        System.out.println("Saque realizado!\nSaldo atual: R$ " + conta.getSaldo());

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
