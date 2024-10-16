package br.com.fiap.coletaverde.dto;

import br.com.fiap.coletaverde.model.Notificacao;

import java.util.Date;

public record NotificacaoExibicaoDTO(long id,
                                     String destinatario,
                                     String assunto,
                                     String mensagem,
                                     Date dataEnvio) {
    public NotificacaoExibicaoDTO(Notificacao notificacao) {
        this(
                notificacao.getId(),
                notificacao.getDestinatario(),
                notificacao.getAssunto(),
                notificacao.getMensagem(),
                notificacao.getDataEnvio());
    }
}
