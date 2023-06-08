package com.bank.database.excecoes;

import java.sql.Connection;

public class DBException extends RuntimeException {

    public DBException (String msg) {
        super(msg);
    }
}
