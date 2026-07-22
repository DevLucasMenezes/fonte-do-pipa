package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private int id;
    private String nomeResponsavel;
    private String endereco;
    private String celular;
    private int diaVencimento; // 👈 Novo campo
    private StatusRede statusRede;

    private final List<CasaObserver> observadores = new ArrayList<>();

    // Construtor completo
    public Casa(int id, String nomeResponsavel, String endereco, String celular, int diaVencimento, StatusRede statusRede) {
        this.id = id;
        this.nomeResponsavel = nomeResponsavel;
        this.endereco = endereco;
        this.celular = celular;
        this.diaVencimento = diaVencimento;
        this.statusRede = statusRede;
    }

    // Construtor auxiliar sem diaVencimento (mantém compatibilidade com NullCasa)
    public Casa(int id, String nomeResponsavel, String endereco, String celular, StatusRede statusRede) {
        this(id, nomeResponsavel, endereco, celular, 10, statusRede); // Valor padrão: dia 10
    }

    // Construtor antigo
    public Casa(int id, String nomeResponsavel, String endereco, StatusRede statusRede) {
        this(id, nomeResponsavel, endereco, "Não informado", 10, statusRede);
    }

    public void adicionarObservador(CasaObserver observador) {
        this.observadores.add(observador);
    }

    public void alterarStatusRede(StatusRede novoStatus) {
        StatusRede antigo = this.statusRede;
        this.statusRede = novoStatus;

        System.out.println("Status da rede da casa ID " + this.id + " alterado para: " + novoStatus);

        for (CasaObserver obs : observadores) {
            obs.onStatusChanged(this, antigo, novoStatus);
        }
    }

    public String obterDadosContrato() {
        return "Contrato ID: " + id +
                " | Responsável: " + nomeResponsavel +
                " | Celular: " + celular +
                " | Endereço: " + endereco +
                " | Dia de Vencimento: " + diaVencimento +
                " | Status: " + statusRede;
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
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public int getDiaVencimento() { return diaVencimento; }
    public void setDiaVencimento(int diaVencimento) { this.diaVencimento = diaVencimento; }
    public StatusRede getStatusRede() { return statusRede; }
    public void setStatusRede(StatusRede statusRede) { this.statusRede = statusRede; }

    @Override
    public String toString() {
        return obterDadosContrato();
    }
}