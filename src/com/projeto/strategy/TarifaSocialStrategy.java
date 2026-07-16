
package com.projeto.strategy;

public class TarifaSocialStrategy implements CalculoTarifaStrategy {
    @Override
    public double calcular(double valorBase) {
        // Aplica 50% de desconto no valor base (multiplica por 0.5)
        return valorBase * 0.5;
    }
}