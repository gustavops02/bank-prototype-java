package com.bank;

import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.servicos.ServicoContas;
import com.bank.servicos.ServicoCRUD;
import com.bank.utilitarios.DesignConfig;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputMismatchException {

        Scanner sc = new Scanner(System.in);

        DesignConfig.layoutInicial();
        System.out.print("Escolha uma opção: ");

        int numero;

        try {
            numero = sc.nextInt();
            sc.nextLine();

            switch (numero) {
                case 1:
                    ServicoCRUD.executarCadastro(sc);
                    break;

                case 2:
                    System.out.print("--> Número da conta: ");
                    int buscaNumeroConta = sc.nextInt();
                    sc.nextLine();
                    System.out.print("--> Seu CPF: ");
                    String buscaCpf = sc.nextLine();

                    try {
                        Conta conta = ServicoContas.buscarConta(buscaNumeroConta, buscaCpf);
                        executarOperacoesConta(sc, conta);

                    } catch (SQLException e) {
                        throw new DBException(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Algo deu errado");
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Encerrado o programa.");
        }

    }

    private static void executarOperacoesConta(Scanner sc, Conta conta) {
        if (conta == null) {
            System.out.print("Conta não encontrada");
            return;
        }

        boolean repetirMenu = true;

        while (repetirMenu) {
            DesignConfig.usarConta(conta);
            System.out.print("Escolha uma opção: ");
            int num = sc.nextInt();
            sc.nextLine();

            switch (num) {
                case 1:
                    ServicoCRUD.consultaSaldo(conta);
                    break;
                case 2:
                    ServicoCRUD.deposito(sc, conta);
                    break;
                case 3:
                    ServicoCRUD.saque(sc, conta);
                    break;
                case 4:
                    ServicoCRUD.remover(sc, conta);
                    break;
                default:
                    System.out.println("Algo deu errado.");
            }

            System.out.print("Deseja realizar outra operação? (S/N): ");
            String confirmacao = sc.nextLine();

            if (!confirmacao.equalsIgnoreCase("S")) {
                repetirMenu = false;
            }

        }
    }

}