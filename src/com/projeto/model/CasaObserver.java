package com.projeto.model;

public interface CasaObserver {
    // Método que será chamado automaticamente quando o status da rede de uma casa mudar
    void onStatusChanged(Casa casa, StatusRede antigoStatus, StatusRede novoStatus);
}