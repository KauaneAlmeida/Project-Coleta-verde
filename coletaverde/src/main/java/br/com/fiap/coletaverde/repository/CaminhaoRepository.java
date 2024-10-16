package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.Caminhao;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface CaminhaoRepository
        extends CrudRepository<Caminhao, Long> {

}
