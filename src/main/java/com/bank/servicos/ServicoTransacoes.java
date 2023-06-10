package com.bank.servicos;


import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;
import com.bank.utilitarios.DesignConfig;

import java.util.Scanner;

public class ServicoTransacoes {

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
            System.out.println("Conta cadastrada!");

        } else if (tipo.equals("CC")) {
            ServicoContas.cadastrarConta(new ContaCorrente(pessoa, saldo));

        } else {
            System.out.println("erro");
        }
    }
}
