package com.bank.database.excecoes;

import java.sql.Connection;
import java.sql.SQLException;

public class DBException extends RuntimeException {

    public DBException (String msg, Connection connection) {
        super(msg);
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
