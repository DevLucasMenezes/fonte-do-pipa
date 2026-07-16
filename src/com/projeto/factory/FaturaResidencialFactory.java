package com.projeto.factory;

import com.projeto.model.Casa;
import com.projeto.model.Mensalidade;
import com.projeto.strategy.TarifaPadraoStrategy;
import java.time.YearMonth;

public class FaturaResidencialFactory extends FaturaFactory {
    @Override
    public Mensalidade criarMensalidade(int id, Casa casa, YearMonth mesReferencia, double valorBase) {
        return new Mensalidade(id, casa, mesReferencia, valorBase, new TarifaPadraoStrategy());
    }
}