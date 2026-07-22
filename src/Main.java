package com.projeto;

import com.projeto.controller.CaminhaoController;
import com.projeto.controller.CasaController;
import com.projeto.controller.ViagemController;
import com.projeto.view.GerenciamentoFonteView;
import com.projeto.view.LoginView;

public class Main {
    public static void main(String[] args) {
        // 1. Tela de Login
        LoginView loginView = new LoginView();
        boolean autenticado = loginView.realizarLogin();

        // 2. Só acessa o sistema se o login for bem-sucedido
        if (autenticado) {
            CaminhaoController caminhaoController = new CaminhaoController();
            CasaController casaController = new CasaController();
            ViagemController viagemController = new ViagemController();

            GerenciamentoFonteView view = new GerenciamentoFonteView(caminhaoController, casaController, viagemController);
            view.exibirMenu();
        } else {
            System.out.println("Encerrando a aplicação por medida de segurança.");
        }
    }
}