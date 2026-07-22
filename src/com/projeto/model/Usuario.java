package com.projeto.model;

public class Usuario {
    private String login;
    private String senha;
    private String nome;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public String getLogin() { return login; }
    public String getNome() { return nome; }
}