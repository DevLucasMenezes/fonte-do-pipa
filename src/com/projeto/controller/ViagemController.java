package com.projeto.controller;

import com.projeto.model.Caminhao;
import com.projeto.model.Viagem;

import java.util.ArrayList;
import java.util.List;

public class ViagemController {
    private final List<Viagem> viagens = new ArrayList<>();
    private int proximoId = 1;

    public void registrarViagem(Caminhao caminhao, double valor) {
        // 1. Atualiza as estatísticas do caminhoneiro
        caminhao.registrarViagem(valor);

        // 2. Cria e guarda o registro histórico da viagem
        Viagem novaViagem = new Viagem(proximoId++, caminhao, valor);
        viagens.add(novaViagem);

        System.out.println("\n✅ Viagem registrada com sucesso!");
        System.out.println(novaViagem.obterDadosViagem());

        // 3. Checa se o motorista atingiu um múltiplo de 20 viagens
        if (caminhao.getQuantidadeViagens() % 20 == 0) {
            System.out.println("\n🎉🎉 PARABÉNS! " + caminhao.getNomeMotorista() + " completou "
                    + caminhao.getQuantidadeViagens() + " viagens e GANHOU UM VOUCHER DE GALETO! 🍗🎉🎉");
        }
    }

    public void listarViagens() {
        System.out.println("\n--- 📜 HISTÓRICO DE VIAGENS REGISTRADAS ---");
        if (viagens.isEmpty()) {
            System.out.println("Nenhuma viagem registrada até o momento.");
        } else {
            double totalArrecadado = 0;
            for (Viagem v : viagens) {
                System.out.println(v.obterDadosViagem());
                totalArrecadado += v.getValor();
            }
            System.out.println("----------------------------------------");
            System.out.println("Total Geral de Viagens: " + viagens.size());
            System.out.println("Total Valor em Viagens: R$ " + String.format("%.2f", totalArrecadado));
        }
        System.out.println("----------------------------------------\n");
    }
}