package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record EnderecoCadastroDTO(Long id,
                                  String cep,
                                  String rua,
                                  String bairro,
                                  int numero,
                                  String complemento,
                                  String cidade,
                                  String estado,
                                  Date createdAt,
                                  Date updatedAt) {
}
