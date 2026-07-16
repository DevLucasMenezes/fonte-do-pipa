package com.projeto.factory;

import com.projeto.model.Casa;
import com.projeto.model.Mensalidade;
import java.time.YearMonth;

public abstract class FaturaFactory {
    public abstract Mensalidade criarMensalidade(int id, Casa casa, YearMonth mesReferencia, double valorBase);
}