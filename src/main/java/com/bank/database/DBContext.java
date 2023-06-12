package com.bank.database;

import com.bank.database.excecoes.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    protected static final String url = "jdbc:mysql://localhost:3306/banking";
    protected static final String host = "root";
    protected static final String senha = "";

    private static Connection connection = null;

    public static Connection getConexao() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, host, senha);

            } catch (SQLException erro) {
                throw new DBException(erro.getMessage(), connection);
            }
        }
        return connection;
    }

    public static void fecharConexao() {

        if (connection instanceof Connection) {

            try {
                connection.close();

            } catch (SQLException e) {
                throw new DBException(e.getMessage(), connection);

            }
        }
    }
}