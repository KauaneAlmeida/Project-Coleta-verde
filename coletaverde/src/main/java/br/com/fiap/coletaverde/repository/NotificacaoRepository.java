package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.Notificacao;
import org.springframework.data.repository.CrudRepository;

public interface NotificacaoRepository
        extends CrudRepository<Notificacao, Long> {
}
