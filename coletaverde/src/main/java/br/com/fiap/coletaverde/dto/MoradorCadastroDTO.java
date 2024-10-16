package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record MoradorCadastroDTO(Long id,
                                 String nome,
                                 long enderecoID,
                                 String senha,
                                 Date createdAt,
                                 Date updatedAt,
                                 String email) {
}
