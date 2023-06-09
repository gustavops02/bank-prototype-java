package com.bank.utilitarios;


import java.util.Scanner;

public class DesignConfig {

    public static void layoutInicial() {

        System.out.print("Bem-vindo ao Sistema Bancário!\n\n");
        System.out.println("Nossas ferramentas:");
        System.out.println("--------------------");
        System.out.println("1 - Cadastrar conta");
        System.out.println("2 - Consultar saldo");
        System.out.println("3 - Efetuar depósito");
        System.out.println("4 - Efetuar saque");
        System.out.println("--------------------");
    }

    public static void tipoDeConta() {
        System.out.print("Vamos ao cadastro!\n------------------------\n");
        System.out.println("Qual o tipo de conta você deseja?\n");
        System.out.println("--> Conta Corrente [CC]");
        System.out.println("--> Conta poupança [CP]\n");

    }

}
