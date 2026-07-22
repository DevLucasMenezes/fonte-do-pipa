package com.projeto.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Viagem {
    private int id;
    private Caminhao caminhao;
    private double valor;
    private LocalDateTime dataHora;

    public Viagem(int id, Caminhao caminhao, double valor) {
        this.id = id;
        this.caminhao = caminhao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now(); // Pega a data e hora exatas do registro
    }

    public int getId() { return id; }
    public Caminhao getCaminhao() { return caminhao; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }

    public String obterDadosViagem() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "ID: " + id +
                " | Motorista: " + caminhao.getNomeMotorista() +
                " | Placa: " + caminhao.getPlaca() +
                " | Valor: R$ " + String.format("%.2f", valor) +
                " | Data/Hora: " + dataHora.format(formatter);
    }
}