package com.bank.servicos;


import com.bank.database.DBContext;
import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.bank.constantes.ConstantesQueries.*;

public class ServicoContas {

    private static final Connection conn = DBContext.getConexao();

    public static void cadastrarConta(Conta conta) {

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT)) {

                stmt.setInt(1, conta.getNumeroConta());
                stmt.setString(2, conta.getTitular().getNome());
                stmt.setString(3, conta.getTitular().getCpf());

                String tipoConta = (conta instanceof ContaCorrente) ? "CC" : "CP";
                stmt.setString(4, tipoConta);
                stmt.setDouble(5, conta.getSaldo());

                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new DBException(e.getMessage());
            } finally {

                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void deletarConta(Conta conta) {

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {

                stmt.setInt(1, conta.getNumeroConta());
                stmt.setString(2, conta.getTitular().getCpf());

                stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new DBException(e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Conta buscarConta(int numeroConta, String cpf) {
        Conta conta = null;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM contas WHERE numeroConta = " + numeroConta + " AND cpf = " + cpf)){

            while (rs.next()) {
                int numConta = rs.getInt("numeroConta");
                if (rs.getString("tipoConta").equals("CC")) {
                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaCorrente(pessoa, rs.getDouble("saldo"));
                    conta.setNumeroConta(numConta);
                    conta.setId(rs.getInt("id"));

                } else if (rs.getString("tipoConta").equals("CP")) {

                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaPoupanca(pessoa, rs.getDouble("saldo"));
                    conta.setNumeroConta(numConta);
                    conta.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return conta;
    }

    public static void deposito(Conta conta, double valor) {

        try {
            conn.setAutoCommit(false);
            conta.deposito(valor);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

                stmt.setDouble(1, conta.getSaldo());
                stmt.setInt(2, conta.getNumeroConta());
                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new DBException(e.getMessage());

            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void saque(Conta conta, double valor) {

        try {
            conn.setAutoCommit(false);

            conta.saque(valor);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

                stmt.setDouble(1, conta.getSaldo());
                stmt.setInt(2, conta.getNumeroConta());

                stmt.executeUpdate();
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new DBException(e.getMessage());

            } finally {
                conn.setAutoCommit(true);

            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static List<Conta> buscarContas() {
        List<Conta> contas = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contas;")) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Conta conta = null;
                Pessoa pessoa = new Pessoa(rs.getString(4), rs.getString(3));
                int numConta = rs.getInt(2);
                if (rs.getString(5).equals("CC")) {
                    conta = new ContaCorrente(pessoa, rs.getDouble(6));
                    conta.setId(rs.getInt(1));
                    conta.setNumeroConta(rs.getInt(2));
                    contas.add(conta);
                } else {
                    conta = new ContaPoupanca(pessoa, rs.getDouble(6));
                    conta.setId(rs.getInt(1));
                    conta.setNumeroConta(rs.getInt(2));
                    contas.add(conta);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as contas: " + e.getMessage());
        }

        return contas;
    }

}
