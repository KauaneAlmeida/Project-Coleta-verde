package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record RotaCadastroDTO(long id,
                              String nome,
                              Date createdAt,
                              Date updatedAt) {
}
