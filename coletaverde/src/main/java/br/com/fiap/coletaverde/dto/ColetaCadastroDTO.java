package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Caminhao;
import br.com.fiap.coletaverde.model.Endereco;
import br.com.fiap.coletaverde.model.Rota;

import java.util.Date;

public record ColetaCadastroDTO(Long id,
                                Rota rotaId,
                                Caminhao caminhaoId,
                                Endereco enderecoId,
                                Date previsaoHorario,
                                Date createdAt,
                                Date updatedAt) {
}
