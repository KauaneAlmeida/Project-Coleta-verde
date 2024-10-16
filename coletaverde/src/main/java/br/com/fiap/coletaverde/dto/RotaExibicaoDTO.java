package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Rota;

import java.util.Date;

public record RotaExibicaoDTO(long id,
                              String nome,
                              Date createdAt,
                              Date updatedAt) {
    public RotaExibicaoDTO(Rota rota) {
        this(
                rota.getId(),
                rota.getNome(),
                rota.getCreatedAt(),
                rota.getUpdatedAt()
        );
    }
}
