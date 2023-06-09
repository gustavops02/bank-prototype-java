package com.bank.servicos;


import com.bank.database.DBContext;
import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;

import java.sql.*;

public class ServicoContas {

    private static final Connection conn = DBContext.getConexao();;
    private static PreparedStatement stmt = null;

    public static void cadastrarConta(Conta conta) {
        try {
            stmt = conn.prepareStatement(
                "INSERT INTO contas "
                    + "(id, numeroConta, titular, cpf, tipoConta, saldo) "
                    + "VALUES "
                    + "(null, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, conta.getNumeroConta());
            stmt.setString(2, conta.getTitular().getNome());
            stmt.setString(3, conta.getTitular().getCpf());

            String tipoConta = (conta instanceof ContaCorrente) ? "CC" : "CP";
            stmt.setString(4, tipoConta);
            stmt.setDouble(5, conta.getSaldo());

            int linhas = stmt.executeUpdate();

            if (linhas >= 1) {
                System.out.println("cadastrado");
            }

        } catch(SQLException e) {
            throw new DBException(e.getMessage());

        } finally {
            DBContext.fecharConexao();
        }
    }

    public static void deletarConta (int id) {
        try {
            stmt = conn.prepareStatement("DELETE FROM contas WHERE id = " + id);
            int linhas = stmt.executeUpdate();

            if(linhas == 1) {
                System.out.println("Deletado");
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
