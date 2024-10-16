package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Endereco;

import java.util.Date;

public record EnderecoExibicaoDTO(Long id,
                                  String cep,
                                  String rua,
                                  String bairro,
                                  int numero,
                                  String complemento,
                                  String cidade,
                                  String estado,
                                  Date createdAt,
                                  Date updatedAt) {
    public EnderecoExibicaoDTO (Endereco endereco){
        this(
                endereco.getId(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCreatedAt(),
                endereco.getUpdatedAt()
        );
    }
}
