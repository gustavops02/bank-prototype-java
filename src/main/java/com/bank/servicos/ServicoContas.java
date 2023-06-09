package com.bank.servicos;


import com.bank.database.DBContext;
import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoContas {

    private static final Connection conn = DBContext.getConexao();;
    private static PreparedStatement stmt = null;

    public static void cadastrarConta(Conta conta) {
        try {

            stmt = conn.prepareStatement(
                "INSERT INTO contas "
                    + "(numeroConta, titular, cpf, tipoConta, saldo) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)"
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

}
