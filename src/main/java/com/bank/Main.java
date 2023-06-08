package com.bank;


import com.bank.database.DBContext;
import com.bank.utilitarios.DesignConfig;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conn = DBContext.getConexao();

        DesignConfig.tipoDeConta();

    }

}