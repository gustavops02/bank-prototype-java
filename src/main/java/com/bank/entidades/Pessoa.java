package com.bank.entidades;

public class Pessoa {

    private String cpf;
    private String nome;

    public Pessoa(){
    }

    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
