package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository
        extends CrudRepository<Endereco, Long> {
}
