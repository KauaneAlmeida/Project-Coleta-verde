package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Caminhao;
import br.com.fiap.coletaverde.model.Coleta;
import br.com.fiap.coletaverde.model.Endereco;
import br.com.fiap.coletaverde.model.Rota;

import java.util.Date;

public record ColetaExibicaoDTO(Long id,
                                Rota rotaId,
                                Caminhao caminhaoId,
                                Endereco enderecoId,
                                Date previsaoHorario,
                                Date createdAt,
                                Date updatedAt) {
    public ColetaExibicaoDTO (Coleta coleta){
        this(
                coleta.getId(),
                coleta.getRotaID(),
                coleta.getCaminhaoID(),
                coleta.getEnderecoID(),
                coleta.getPrevisaoHorario(),
                coleta.getCreatedAt(),
                coleta.getUpdatedAt()
        );
    }
}
