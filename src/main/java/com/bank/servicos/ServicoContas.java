package com.bank.servicos;


import com.bank.database.DBContext;
import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;

import java.sql.*;

public class ServicoContas {

    private static final Connection conn = DBContext.getConexao();

    private static final String QUERY_INSERT = "INSERT INTO contas (id, numeroConta, titular, cpf, tipoConta, saldo) VALUES (null, ?, ?, ?, ?, ?)";
    private static String QUERY_UPDATE = "UPDATE contas SET saldo = ? WHERE numeroConta = ?";
    private static final String QUERY_DELETE = "DELETE FROM contas WHERE numeroConta = ? AND cpf = ?";

    public static void cadastrarConta(Conta conta) {

        try { // TRY para a transação
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

    public static Conta buscarConta(int numeroConta, String cpf) throws SQLException {
        Conta conta = null;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM contas WHERE numeroConta = " + numeroConta + " AND cpf = " + cpf)){

            while (rs.next()) {
                int numConta = rs.getInt("numeroConta");
                if (rs.getString("tipoConta").equals("CC")) {
                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaCorrente(pessoa, rs.getDouble("saldo"));
                    conta.setNumeroConta(numConta);

                } else if (rs.getString("tipoConta").equals("CP")) {

                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaPoupanca(pessoa, rs.getDouble("saldo"));
                    conta.setNumeroConta(numConta);
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return conta;
    }

    public static void deposito(ContaCorrente contaCorrente, double valor) {
        try {
            conn.setAutoCommit(false);

            contaCorrente.deposito(valor);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

                stmt.setDouble(1, contaCorrente.getSaldo());
                stmt.setInt(2, contaCorrente.getNumeroConta());
                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();

                System.out.println(e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void deposito(ContaPoupanca contaPoupanca, double valor) {

        try {
            conn.setAutoCommit(false);
            contaPoupanca.deposito(valor);

            try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

                stmt.setDouble(1, contaPoupanca.getSaldo());
                stmt.setInt(2, contaPoupanca.getNumeroConta());
                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();

                System.out.println(e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


    public static void saque(ContaCorrente conta, double valor) {

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
                System.out.println(e.getMessage());

            } finally {
                conn.setAutoCommit(true);

            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void saque(ContaPoupanca conta, double valor) {
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
                System.out.println(e.getMessage());

            } finally {
                conn.setAutoCommit(true);

            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }
}
