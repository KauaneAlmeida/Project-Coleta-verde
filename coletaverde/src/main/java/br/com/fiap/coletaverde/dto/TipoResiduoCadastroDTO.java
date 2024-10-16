package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record TipoResiduoCadastroDTO(long id,
                                     String nome,
                                     String descricao,
                                     Date createdAt,
                                     Date updatedAt) {
}
