package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Caminhao;

import java.util.Date;

public record CaminhaoExibicaoDTO(Long id,
                                  String placa,
                                  String coordenadaX,
                                  String coordenadaY,
                                  Date createdAt,
                                  Date updatedAt) {
    public CaminhaoExibicaoDTO(Caminhao caminhao){
        this(
                caminhao.getId(),
                caminhao.getPlaca(),
                caminhao.getCoordenadaX(),
                caminhao.getCoordenadaY(),
                caminhao.getCreatedAt(),
                caminhao.getUpdatedAt());
    }
}
