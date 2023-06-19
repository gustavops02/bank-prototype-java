package com.bank;

import com.bank.entidades.Conta;
import com.bank.servicos.ServicoContas;
import com.bank.utilitarios.UsarServico;
import com.bank.utilitarios.DesignConfig;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DesignConfig.layoutInicial();
        System.out.print("Escolha uma opção: ");
        int numero;

        try {
            numero = sc.nextInt();
            sc.nextLine();

            switch (numero) {
                case 1:
                    Conta novaConta = UsarServico.executarCadastro(sc);
                    executarOperacoesConta(sc, novaConta);
                    break;

                case 2:
                    System.out.print("--> Número da conta: ");
                    int buscaNumeroConta = sc.nextInt();
                    sc.nextLine();
                    System.out.print("--> Seu CPF: ");
                    String buscaCpf = sc.nextLine();

                    Conta conta = ServicoContas.buscarConta(buscaNumeroConta, buscaCpf);
                    executarOperacoesConta(sc, conta);

                    break;
                case 3:

                    List<Conta> contas = ServicoContas.buscarContas();
                    for (Conta c: contas) {
                        System.out.println("\n");
                        System.out.println(c);
                    }
                    break;
                default:
                    System.err.println("Algo deu errado");
            }

        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Encerrado o programa...");
        }

    }

    private static void executarOperacoesConta(Scanner sc, Conta conta) {
        if (conta == null) {
            System.err.print("Conta não encontrada");
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
                    UsarServico.consultaSaldo(conta);
                    System.out.println("Pressione Enter para sair");
                    break;
                case 2:
                    UsarServico.deposito(sc, conta);
                    break;
                case 3:
                    UsarServico.saque(sc, conta);
                    break;
                case 4:
                    UsarServico.remover(sc, conta);
                    break;
                default:
                    System.err.println("Algo deu errado.");
            }
            sc.nextLine();
            System.out.print("Deseja realizar outra operação? (S/N): ");
            String confirmacao = sc.nextLine();

            if (!confirmacao.equalsIgnoreCase("S")) {
                repetirMenu = false;
            }

        }
    }

}