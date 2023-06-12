package com.bank.constantes;

public class ConstantesQueries {
    public static final String QUERY_INSERT = "INSERT INTO contas (id, numeroConta, titular, cpf, tipoConta, saldo) VALUES (null, ?, ?, ?, ?, ?)";
    public static final String QUERY_UPDATE = "UPDATE contas SET saldo = ? WHERE numeroConta = ?";
    public static final String QUERY_DELETE = "DELETE FROM contas WHERE numeroConta = ? AND cpf = ?";
}
