package com.projeto.view;

import com.projeto.controller.CaminhaoController;
import com.projeto.controller.CasaController;
import com.projeto.controller.ViagemController;
import com.projeto.model.Caminhao;

import java.util.List;
import java.util.Scanner;

public class GerenciamentoFonteView {
    private CaminhaoController caminhaoController;
    private CasaController casaController;
    private ViagemController viagemController;
    private Scanner scanner;

    public GerenciamentoFonteView(CaminhaoController caminhaoController, CasaController casaController, ViagemController viagemController) {
        this.caminhaoController = caminhaoController;
        this.casaController = casaController;
        this.viagemController = viagemController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========== 🚰 FONTE DO PIPA - SISTEMA UNIFICADO ==========");
            System.out.println("--- 🚛 GERENCIAMENTO DE CAMINHÕES ---");
            System.out.println("1 - Cadastrar Novo Caminhão");
            System.out.println("2 - Listar Caminhões Cadastrados");

            System.out.println("\n--- 💧 VIAGENS E FIDELIDADE (GALETO) ---");
            System.out.println("3 - Registrar Nova Viagem (R$ 20,00)");
            System.out.println("4 - Listar Histórico de Viagens");
            System.out.println("5 - Ver Carteira do Caminhoneiro (Vouchers & Débitos)");

            System.out.println("\n--- 🏠 GERENCIAMENTO DE CASAS ---");
            System.out.println("6 - Cadastrar Nova Casa");
            System.out.println("7 - Listar Casas Cadastradas");

            System.out.println("\n0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarCaminhaoView();
                    aguardarEnter();
                    break;
                case 2:
                    caminhaoController.listarCaminhoes();
                    aguardarEnter();
                    break;
                case 3:
                    registrarViagemView();
                    aguardarEnter();
                    break;
                case 4:
                    viagemController.listarViagens();
                    aguardarEnter();
                    break;
                case 5:
                    caminhaoController.exibirRelatorioVouchersEDebitos();
                    aguardarEnter();
                    break;
                case 6:
                    cadastrarCasaView();
                    aguardarEnter();
                    break;
                case 7:
                    casaController.listarCasas();
                    aguardarEnter();
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    aguardarEnter();
            }
        }
    }

    private void cadastrarCaminhaoView() {
        System.out.println("\n--- 🚛 CADASTRO DE NOVO CAMINHÃO ---");
        System.out.print("Digite a Placa do caminhão: ");
        String placa = scanner.nextLine();

        System.out.print("Digite o Nome do motorista: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o Celular do motorista: ");
        String celular = scanner.nextLine();

        caminhaoController.cadastrarCaminhao(placa, nome, celular);
    }

    private void registrarViagemView() {
        System.out.println("\n--- 🚚 REGISTRO DE VIAGEM (CARRADA D'ÁGUA) ---");
        List<Caminhao> caminhoes = caminhaoController.obterListaCaminhoes();

        if (caminhoes == null || caminhoes.isEmpty()) {
            System.out.println("❌ Nenhum caminhão cadastrado no sistema! Cadastre um caminhão primeiro.");
            return;
        }

        System.out.println("Selecione o caminhão que realizou a viagem:");
        for (int i = 0; i < caminhoes.size(); i++) {
            Caminhao c = caminhoes.get(i);
            System.out.println((i + 1) + " - Placa: " + c.getPlaca() + " | Motorista: " + c.getNomeMotorista());
        }

        System.out.print("Opção do Caminhão (número): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > caminhoes.size()) {
            System.out.println("❌ Opção de caminhão inválida!");
            return;
        }

        Caminhao caminhaoselecionado = caminhoes.get(escolha - 1);
        double valorPadrao = 20.00;

        viagemController.registrarViagem(caminhaoselecionado, valorPadrao);
    }

    private void cadastrarCasaView() {
        System.out.println("\n--- 🏠 CADASTRO DE NOVA CASA ---");

        System.out.print("Digite o Nome do responsável pela casa: ");
        String nomeResponsavel = scanner.nextLine();

        System.out.print("Digite o Endereço da casa: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite o Celular do responsável: ");
        String celular = scanner.nextLine();

        System.out.print("Digite o Dia do vencimento do pagamento (ex: 5, 10, 15): ");
        int diaVencimento = scanner.nextInt();
        scanner.nextLine();

        casaController.cadastrarCasa(nomeResponsavel, endereco, celular, diaVencimento);
    }

    private void aguardarEnter() {
        System.out.println("\nPressione [ENTER] para voltar ao menu principal...");
        scanner.nextLine();
    }
}