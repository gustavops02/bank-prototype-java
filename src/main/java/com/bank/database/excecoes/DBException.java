package com.bank.database.excecoes;


import com.bank.database.DBContext;

public class DBException extends RuntimeException {

    public DBException (String msg) {
        super(msg);
        DBContext.fecharConexao();
    }

}
