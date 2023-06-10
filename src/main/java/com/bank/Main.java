package com.bank;

import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;

import com.bank.servicos.ServicoContas;
import com.bank.servicos.ServicoTransacoes;
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
                ServicoTransacoes.executarCadastro(sc);
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

    public static void cadastro() {

    }
}