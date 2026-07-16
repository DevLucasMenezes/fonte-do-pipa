package com.projeto.strategy;

public interface CalculoTarifaStrategy {
    // Todo cálculo de tarifa precisará receber um valor base e devolver o valor final calculado
    double calcular(double valorBase);
}