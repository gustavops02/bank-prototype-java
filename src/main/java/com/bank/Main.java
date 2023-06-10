package com.bank;

import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;
import com.bank.servicos.ServicoContas;
import com.bank.utilitarios.DesignConfig;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DesignConfig.layoutInicial();
        System.out.print("Escolha uma opção: ");

        int numero = sc.nextInt();
        sc.nextLine(); // Remover o enter do buffer.

        if (numero > 4) {
            throw new IllegalArgumentException("Numero não encontrado. Abra novamente");
        }

        switch (numero) {
            case 1:
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
                break;
            case 2:
                System.out.print("--> Número da conta: ");
                int buscaNumeroConta = sc.nextInt();
                sc.nextLine();
                System.out.print("--> Seu CPF: ");
                String buscaCpf = sc.nextLine();

                try {
                   Conta conta = ServicoContas.buscarConta(buscaNumeroConta, buscaCpf);
                    System.out.println(conta);
                } catch (SQLException e) {
                    throw new DBException(e.getMessage());
                }
                break;
        }


    }
}