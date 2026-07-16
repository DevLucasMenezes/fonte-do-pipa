package com.projeto.model;

import com.projeto.strategy.CalculoTarifaStrategy;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Mensalidade {
    private int id;
    private Casa casa;
    private YearMonth mesReferencia; // Mudamos de String para YearMonth!
    private double valorCobrado;
    private boolean paga;

    public Mensalidade(int id, Casa casa, YearMonth mesReferencia, double valorBase, CalculoTarifaStrategy estrategia) {
        this.id = id;
        this.casa = casa;
        this.mesReferencia = mesReferencia;
        this.paga = false;
        this.valorCobrado = estrategia.calcular(valorBase);
    }

    public void registrarPagamento() {
        this.paga = true;
        System.out.println("LOG: Mensalidade ID " + this.id + " da casa de " + casa.getNomeResponsavel() + " foi QUITADA!");
    }

    // Método para exibir o relatório da fatura de forma elegante
    public String obterDadosFatura() {
        String statusPagamento = paga ? "PAGA" : "PENDENTE";
        return "Fatura ID: " + id +
                " | Responsável: " + casa.getNomeResponsavel() +
                " | Mês: " + mesReferencia +
                " | Valor: R$ " + valorCobrado +
                " | Status: " + statusPagamento;
    }

    // Getters e Setters atualizados
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Casa getCasa() { return casa; }
    public void setCasa(Casa casa) { this.casa = casa; }
    public YearMonth getMesReferencia() { return mesReferencia; }
    public void setMesReferencia(YearMonth mesReferencia) { this.mesReferencia = mesReferencia; }
    public double getValorCobrado() { return valorCobrado; }
    public void setValorCobrado(double valorCobrado) { this.valorCobrado = valorCobrado; }
    public boolean isPaga() { return paga; }
    public void setPaga(boolean paga) { this.paga = paga; }
}