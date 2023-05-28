package com.bank.entities;

public class Pessoa {

    private String cpf;
    private String name;

    public Pessoa(){
    }

    public Pessoa(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

}
