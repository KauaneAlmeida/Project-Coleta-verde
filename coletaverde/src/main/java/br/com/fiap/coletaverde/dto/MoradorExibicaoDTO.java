package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Endereco;
import br.com.fiap.coletaverde.model.Morador;

import java.util.Date;

public record MoradorExibicaoDTO(Long id,
                                 String nome,
                                 long enderecoID,
                                 String senha,
                                 Date createdAt,
                                 Date updatedAt,
                                 String email) {
    public MoradorExibicaoDTO(Morador morador) {
        this(
                morador.getId(),
                morador.getNome(),
                morador.getEnderecoID(),
                morador.getSenha(),
                morador.getCreatedAt(),
                morador.getUpdatedAt(),
                morador.getEmail()
        );
    }
}
