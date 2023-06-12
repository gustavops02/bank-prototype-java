package com.bank.utilitarios;

import com.bank.entidades.Conta;

public class DesignConfig {

    public static void layoutInicial() {

        System.out.print("Bem-vindo ao Sistema Bancário!\n\n");
        System.out.println("Nossas ferramentas:");
        System.out.println("--------------------");
        System.out.println("1 - Cadastrar conta");
        System.out.println("2 - Usar conta");
        System.out.println("--------------------");
    }

    public static void tipoDeConta() {
        System.out.print("Vamos ao cadastro!\n------------------------\n");
        System.out.println("Qual o tipo de conta você deseja?\n");
        System.out.println("--> Conta Corrente [CC]");
        System.out.println("--> Conta poupança [CP]\n");

    }

    public static void usarConta(Conta conta) {
        System.out.print("\n\n");
        System.out.println("Olá, " + conta.getTitular().getNome());
        System.out.println("O que deseja fazer? ");
        System.out.println("--------------------");
        System.out.println("1 - Consultar saldo");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Excluir conta");

    }

}
