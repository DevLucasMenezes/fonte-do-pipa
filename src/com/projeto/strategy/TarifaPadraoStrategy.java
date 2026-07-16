package com.projeto.strategy;

public class TarifaPadraoStrategy implements CalculoTarifaStrategy {
    @Override
    public double calcular(double valorBase) {
        // Na tarifa padrão, o valor cobrado é exatamente o valor base fixado
        return valorBase;
    }
}
