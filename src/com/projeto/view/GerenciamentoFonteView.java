package com.projeto.view;

import com.projeto.controller.CasaController;
import com.projeto.model.StatusRede;
import java.util.Scanner;

public class GerenciamentoFonteView {
    private final CasaController casaController;
    private final Scanner scanner;

    // Construtor que recebe o cérebro do sistema (o controlador)
    public GerenciamentoFonteView(CasaController casaController) {
        this.casaController = casaController;
        this.scanner = new Scanner(System.in);
    }

    // Método que exibe o menu e fica escutando os comandos no console
    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=======================================");
            System.out.println("     SISTEMA DE GESTÃO - FONTE DO PIPA ");
            System.out.println("=======================================");
            System.out.println("1 - Cadastrar Nova Casa");
            System.out.println("2 - Forçar Atualização de Faturamento (Virada de Mês)");
            System.out.println("3 - Registrar Pagamento de Fatura");
            System.out.println("4 - Alterar Status da Rede Hidráulica");
            System.out.println("5 - Exibir Relatório de Faturas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    executarCadastroCasa();
                    break;
                case 2:
                    executarGerarFatura();
                    break;
                case 3:
                    executarPagamentoFatura();
                    break;
                case 4:
                    executarAlterarStatus();
                    break;
                case 5:
                    casaController.listarTodasFaturas();
                    System.out.print("\nPressione ENTER para voltar ao menu...");
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema da Fonte do Pipa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void executarCadastroCasa() {
        System.out.print("Digite o ID da Casa (Número): ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do Responsável: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço Completo: ");
        String endereco = scanner.nextLine();

        casaController.cadastrarCasa(id, nome, endereco);

        // Pressione Enter para continuar
        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    private void executarGerarFatura() {
        System.out.println("\n[Processando virada de mês...] Verificando novas faturas do sistema...");

        // Pegamos o mês atual real do computador
        java.time.YearMonth mesAtual = java.time.YearMonth.now();

        // Dispara o motor automático considerando a taxa padrão de 50 reais
        casaController.processarFaturamentoAutomatico(mesAtual, 50.0);

        System.out.println("Faturamento atualizado com sucesso!");
        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    private void executarPagamentoFatura() {
        System.out.print("Digite o ID da Fatura a Pagar: ");
        int idFatura = Integer.parseInt(scanner.nextLine());
        casaController.registrarPagamentoFatura(idFatura);

        // Pressione Enter para continuar
        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    private void executarAlterarStatus() {
        System.out.print("Digite o ID da Casa: ");
        int idCasa = Integer.parseInt(scanner.nextLine());
        System.out.println("Selecione o Novo Status:");
        System.out.println("1 - ATIVA");
        System.out.println("2 - SUSPENSA");
        System.out.println("3 - EM MANUTENÇÃO");
        System.out.print("Opção: ");
        int optStatus = Integer.parseInt(scanner.nextLine());

        StatusRede novoStatus = StatusRede.ATIVA;
        if (optStatus == 2) novoStatus = StatusRede.SUSPENSA;
        if (optStatus == 3) novoStatus = StatusRede.EM_MANUTENCAO;

        casaController.atualizarStatusRede(idCasa, novoStatus);

        // Pressione Enter para continuar
        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }
}