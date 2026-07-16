package com.projeto.repository;

// Precisamos importar as classes do pacote model para que o repositório as conheça
import com.projeto.model.Casa;
import com.projeto.model.NullCasa;
import java.util.ArrayList;
import java.util.List;

public class CasaRepository {
    // Criamos uma lista dinâmica na memória para simular a nossa tabela do banco de dados
    private final List<Casa> listaCasas = new ArrayList<>();

    // Método para salvar/cadastrar uma nova casa na nossa lista
    public void salvar(Casa casa) {
        listaCasas.add(casa);
        System.out.println("LOG: Casa de " + casa.getNomeResponsavel() + " salva com sucesso no repositório!");
    }

    // Método para listar todas as casas cadastradas
    public List<Casa> listarTodas() {
        return listaCasas;
    }

    // O coração do Null Object: Método para buscar uma casa pelo ID
    public Casa buscarPorId(int id) {
        for (Casa casa : listaCasas) {
            if (casa.getId() == id) {
                return casa; // Se achar a casa real, retorna ela!
            }
        }

        /* ATENÇÃO AQUI: Em um código comum, se não achasse nada, retornaríamos 'null'.
          Mas aqui, nós retornamos uma nova 'NullCasa()'.
          Isso garante que o Controller NUNCA vai receber um valor nulo perigoso.
        */
        return new NullCasa();
    }
}