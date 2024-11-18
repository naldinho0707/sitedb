package com.bd.sitebd.model;

public class Cliente {

    private int id;
    private String nome, cpf;

    // construtor vazio para criar a tabela
    public Cliente(){
    }

    // porque 02 contrutores 01 para o banco de dados sem id porque o banco cria i Id (autoincremento)
    // sobrecarga dos contrutores Cliente
    // este construtor para poder alterar os dados
    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // porque 02 contrutores este para o banco de dados sem id porque o banco cria i Id (autoincremento)
    //  não preciso de id para cadastrar, por isso uso a sobrecarga
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
