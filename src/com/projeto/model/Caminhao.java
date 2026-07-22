package com.projeto.model;

public class Caminhao {
    private String placa;
    private String nomeMotorista;
    private String celular;
    private int quantidadeViagens;
    private double saldoDevedor;

    public Caminhao(String placa, String nomeMotorista, String celular) {
        this.placa = placa;
        this.nomeMotorista = nomeMotorista;
        this.celular = celular;
        this.quantidadeViagens = 0;
        this.saldoDevedor = 0.0;
    }

    // Registra uma nova viagem e atualiza os débitos
    public void registrarViagem(double valorViagem) {
        this.quantidadeViagens++;
        this.saldoDevedor += valorViagem;
    }

    // Permite abater/pagar o saldo devedor
    public void abaterSaldo(double valorPago) {
        this.saldoDevedor -= valorPago;
        if (this.saldoDevedor < 0) {
            this.saldoDevedor = 0;
        }
    }

    // Total de vouchers de galeto conquistados (1 a cada 20 viagens)
    public int getVouchersGanhos() {
        return this.quantidadeViagens / 20;
    }

    // Viagens no ciclo atual (ex: 5 de 20)
    public int getProgressoProximoVoucher() {
        return this.quantidadeViagens % 20;
    }

    public String obterResumoCarteira() {
        int vouchers = getVouchersGanhos();
        int progresso = getProgressoProximoVoucher();
        int faltam = 20 - progresso;

        return String.format(
                "🚛 Motorista: %s | Placa: %s\n" +
                        "   • Viagens Realizadas: %d\n" +
                        "   • Saldo Devedor: R$ %.2f\n" +
                        "   • Vouchers de Galeto Conquistados: 🍗 %d\n" +
                        "   • Progresso para o próximo Galeto: %d/20 (Faltam %d viagens!)\n",
                nomeMotorista, placa, quantidadeViagens, saldoDevedor, vouchers, progresso, faltam
        );
    }

    // Getters e Setters
    public String getPlaca() { return placa; }
    public String getNomeMotorista() { return nomeMotorista; }
    public String getCelular() { return celular; }
    public int getQuantidadeViagens() { return quantidadeViagens; }
    public double getSaldoDevedor() { return saldoDevedor; }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Motorista: " + nomeMotorista + " | Celular: " + celular;
    }
}