package com.projeto.controller;

import com.projeto.factory.FaturaFactory;
import com.projeto.factory.FaturaResidencialFactory;
import com.projeto.model.Casa;
import com.projeto.model.Mensalidade;
import com.projeto.model.StatusRede;
import com.projeto.repository.CasaRepository;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CasaController {
    private final CasaRepository casaRepository;
    private final List<Mensalidade> mensalidades = new ArrayList<>();
    private final FaturaFactory faturaFactory = new FaturaResidencialFactory();
    private int proximoIdMensalidade = 1;

    public CasaController(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    public void cadastrarCasa(int id, String nome, String endereco) {
        Casa novaCasa = new Casa(id, nome, endereco, StatusRede.ATIVA);
        casaRepository.salvar(novaCasa);

        // Assim que cadastrar uma casa nova, já roda o faturamento automático para ela ganhar a fatura do mês atual!
        processarFaturamentoAutomatico(YearMonth.now(), 50.0);
    }

    /**
     * MOTOR AUTOMÁTICO: Este método varre todas as casas e gera as faturas atrasadas/atuais
     * com base no mês informado (YearMonth) e em um valor base padrão de tarifa.
     */
    public void processarFaturamentoAutomatico(YearMonth mesAlvo, double valorBase) {
        List<Casa> todasCasas = casaRepository.listarTodas();

        for (Casa casa : todasCasas) {
            // Se a casa estiver suspensa ou em manutenção, não gera cobrança automática
            if (casa.getStatusRede() != StatusRede.ATIVA) {
                continue;
            }

            // Verifica se essa casa já possui uma mensalidade para este mês específico
            boolean jaTemFatura = false;
            for (Mensalidade m : mensalidades) {
                if (m.getCasa().getId() == casa.getId() && m.getMesReferencia().equals(mesAlvo)) {
                    jaTemFatura = true;
                    break;
                }
            }

            // Se a casa NÃO possui fatura para o mês, a fábrica gera uma automaticamente!
            if (!jaTemFatura) {
                Mensalidade novaFatura = faturaFactory.criarMensalidade(proximoIdMensalidade++, casa, mesAlvo, valorBase);
                mensalidades.add(novaFatura);
                System.out.println("[SISTEMA AUTOMÁTICO]: Nova fatura lançada como PENDENTE para a casa de "
                        + casa.getNomeResponsavel() + " referente a " + mesAlvo);
            }
        }
    }

    public void registrarPagamentoFatura(int idMensalidade) {
        for (Mensalidade m : mensalidades) {
            if (m.getId() == idMensalidade) {
                m.registrarPagamento();
                return;
            }
        }
        System.out.println("ERRO: Fatura com ID " + idMensalidade + " não foi encontrada!");
    }

    public void atualizarStatusRede(int idCasa, StatusRede novoStatus) {
        Casa casa = casaRepository.buscarPorId(idCasa);
        if (casa.isNull()) {
            System.out.println("ERRO: Não foi possível alterar status. Casa ID " + idCasa + " não existe!");
            return;
        }
        casa.alterarStatusRede(novoStatus);
    }

    // Ação 5: Listar todas as faturas do sistema no console
    public void listarTodasFaturas() {
        System.out.println("\n--- RELATÓRIO GERAL DE MENSALIDADES ---");

        if (mensalidades.isEmpty()) {
            System.out.println("Nenhuma fatura gerada até o momento.");
            return;
        }

        System.out.println("\n=======================================");
        System.out.println("   FATURAS PAGAS");
        System.out.println("=======================================");
        boolean temPagas = false;
        for (Mensalidade m : mensalidades) {
            if (m.isPaga()) {
                System.out.println(m.obterDadosFatura());
                temPagas = true;
            }
        }
        if (!temPagas) {
            System.out.println("Nenhuma fatura paga até o momento.");
        }

        System.out.println("\n=======================================");
        System.out.println("   FATURAS PENDENTES");
        System.out.println("=======================================");
        boolean temPendentes = false;
        for (Mensalidade m : mensalidades) {
            if (!m.isPaga()) {
                System.out.println(m.obterDadosFatura());
                temPendentes = true;
            }
        }
        if (!temPendentes) {
            System.out.println("Nenhuma fatura pendente no momento!");
        }
        System.out.println("=======================================");
    }
}