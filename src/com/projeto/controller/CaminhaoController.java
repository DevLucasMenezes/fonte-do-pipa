package com.projeto.controller;

import com.projeto.model.Caminhao;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoController {
    private List<Caminhao> listaCaminhoes;

    public CaminhaoController() {
        this.listaCaminhoes = new ArrayList<>();
    }
    public List<Caminhao> obterListaCaminhoes() {
        return this.listaCaminhoes;
    }

    // Método para cadastrar um novo caminhão
    public void cadastrarCaminhao(String placa, String nomeMotorista, String celularMotorista) {
        Caminhao novoCaminhao = new Caminhao(placa, nomeMotorista, celularMotorista);
        listaCaminhoes.add(novoCaminhao);
        System.out.println("✅ Caminhão placa [" + placa + "] cadastrado com sucesso!");
    }

    // Método para buscar um caminhão pela placa
    public Caminhao buscarPorPlaca(String placa) {
        for (Caminhao c : listaCaminhoes) {
            if (c.getPlaca().equalsIgnoreCase(placa)) {
                return c;
            }
        }
        return null;
    }
    public void exibirRelatorioVouchersEDebitos() {
        System.out.println("\n--- 🍗 CARTEIRA DOS CAMINHONEIROS (VOUCHERS E DÉBITOS) ---");
        if (listaCaminhoes.isEmpty()) {
            System.out.println("Nenhum caminhão cadastrado no momento.");
            return;
        }
        for (Caminhao c : listaCaminhoes) {
            System.out.println(c.obterResumoCarteira());
        }
        System.out.println("---------------------------------------------------------");
    }

    // Método para listar todos os caminhões cadastrados
    public void listarCaminhoes() {
        System.out.println("\n--- 🚛 LISTA DE CAMINHÕES CADASTRADOS ---");
        if (listaCaminhoes.isEmpty()) {
            System.out.println("Nenhum caminhão cadastrado no momento.");
        } else {
            for (Caminhao c : listaCaminhoes) {
                System.out.println(c);
            }
        }
        System.out.println("-----------------------------------------\n");
    }

    public List<Caminhao> getListaCaminhoes() {
        return listaCaminhoes;
    }
}