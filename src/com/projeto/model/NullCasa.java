package com.projeto.model;

// O termo "extends Casa" significa que a NullCasa herda tudo o que a Casa tem
public class NullCasa extends Casa {

    // Construtor que cria uma casa "fantasma" com dados neutros de aviso
    public NullCasa() {
        super(-1, "Não Encontrado", "Nenhum endereço", StatusRede.SUSPENSA);
    }

    // Sobrescrevemos o método para que ele retorne TRUE.
    // Assim o sistema sabe que esta é uma casa nula/inválida.
    @Override
    public boolean isNull() {
        return true;
    }

    // Sobrescrevemos para que ela não mude de status de verdade
    @Override
    public void alterarStatusRede(StatusRede novoStatus) {
        // Não faz nada, pois é um objeto nulo seguro
    }
}