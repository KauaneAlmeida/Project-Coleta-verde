package br.com.fiap.coletaverde.dto;

import java.util.Date;

public record NotificacaoCadastroDTO(long id,
                                     String destinatario,
                                     String assunto,
                                     String mensagem,
                                     Date dataEnvio) {
}
