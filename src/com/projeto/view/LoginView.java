package com.projeto.view;

import com.projeto.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginView {
    private final List<Usuario> usuariosCadastrados = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public LoginView() {
        // Usuário padrão do sistema da fonte
        usuariosCadastrados.add(new Usuario("admin", "1234", "Administrador da Fonte"));
    }

    public boolean realizarLogin() {
        int tentativas = 0;
        int maxTentativas = 3;

        System.out.println("\n==============================================");
        System.out.println("   🔒 SISTEMA FONTE DO PIPA - AUTENTICAÇÃO    ");
        System.out.println("==============================================");

        while (tentativas < maxTentativas) {
            System.out.print("Usuário: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            for (Usuario u : usuariosCadastrados) {
                if (u.autenticar(login, senha)) {
                    System.out.println("\n✅ Login realizado com sucesso! Bem-vindo(a), " + u.getNome() + "!");
                    return true;
                }
            }

            tentativas++;
            System.out.println("❌ Usuário ou senha incorretos! Tentativa " + tentativas + " de " + maxTentativas + ".\n");
        }

        System.out.println("⛔ Número máximo de tentativas excedido. Acesso negado!");
        return false;
    }
}