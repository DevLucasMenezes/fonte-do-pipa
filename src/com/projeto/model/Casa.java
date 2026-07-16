package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private int id;
    private String nomeResponsavel;
    private String endereco;
    private StatusRede statusRede;

    // AQUI ENTRA O OBSERVER: Uma lista para guardar quem quer vigiar as mudanças de status da casa
    private final List<CasaObserver> observadores = new ArrayList<>();

    public Casa(int id, String nomeResponsavel, String endereco, StatusRede statusRede) {
        this.id = id;
        this.nomeResponsavel = nomeResponsavel;
        this.endereco = endereco;
        this.statusRede = statusRede;
    }

    // Método para registrar um novo interessado em observar a casa
    public void adicionarObservador(CasaObserver observador) {
        this.observadores.add(observador);
    }

    // Atualizamos o método de alteração de status para avisar todo mundo na lista!
    public void alterarStatusRede(StatusRede novoStatus) {
        StatusRede antigo = this.statusRede;
        this.statusRede = novoStatus;

        System.out.println("Status da rede da casa ID " + this.id + " alterado para: " + novoStatus);

        // Dispara o alarme para todos os observadores cadastrados
        for (CasaObserver obs : observadores) {
            obs.onStatusChanged(this, antigo, novoStatus);
        }
    }

    public String obterDadosContrato() {
        return "Contrato ID: " + id + " | Responsável: " + nomeResponsavel + " | Endereço: " + endereco + " | Status: " + statusRede;
    }

    public boolean isNull() {
        return false;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public StatusRede getStatusRede() { return statusRede; }
    public void setStatusRede(StatusRede statusRede) { this.statusRede = statusRede; }
}