package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.TipoResiduo;

import java.util.Date;

public record TipoResiduoExibicaoDTO(long id,
                                     String nome,
                                     String descricao,
                                     Date createdAt,
                                     Date updatedAt) {
    public TipoResiduoExibicaoDTO(TipoResiduo tipoResiduo) {
        this(
                tipoResiduo.getId(),
                tipoResiduo.getNome(),
                tipoResiduo.getDescricao(),
                tipoResiduo.getCreatedAt(),
                tipoResiduo.getUpdatedAt()
        );
    }
}
