package com.bank.entities;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int cpf;
    private String name;

    public Person(){
    }

    public Person(int cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public int getCPF() {
        return cpf;
    }

    public String getName() {
        return name;
    }

}
