package com.bank.servicos;


import com.bank.database.DBContext;
import com.bank.database.excecoes.DBException;
import com.bank.entidades.Conta;
import com.bank.entidades.ContaCorrente;
import com.bank.entidades.ContaPoupanca;
import com.bank.entidades.Pessoa;

import java.sql.*;

public class ServicoContas {

    private static final Connection conn = DBContext.getConexao();;

    private static ResultSet rs = null;

    public static void cadastrarConta(Conta conta) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
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
                System.out.println("Cadastrado");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new DBException(e.getMessage());

        } finally {
            DBContext.fecharConexao();

        }

    }

    public static void deletarConta (int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM contas WHERE id = " + id);
            int linhas = stmt.executeUpdate();

            if(linhas == 1) {
                System.out.println("Deletado");
            }

            stmt.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
    public static Conta buscarConta(int numeroConta, String cpf) throws SQLException {
        Conta conta = null;
        try{
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM contas WHERE numeroConta = " + numeroConta + " AND cpf = " + cpf);

            while (rs.next()) {

                if(rs.getString("tipoConta").equals("CC")) {
                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaCorrente(pessoa, rs.getDouble("saldo"));

                } else if (rs.getString("tipoConta").equals("CP")){
                    Pessoa pessoa = new Pessoa(rs.getString("cpf"), rs.getString("titular"));
                    conta = new ContaPoupanca(pessoa, rs.getDouble("saldo"));
                }
            }

        } catch(SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DBContext.fecharConexao();
            rs.close();
        }
        return conta;
    }
}
