package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.TipoResiduo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TipoResiduoRepository
        extends CrudRepository<TipoResiduo, Long> {
    @Query("SELECT a FROM TipoResiduo a WHERE a.nome = :nome")
    Optional<TipoResiduo> buscarPorNome(@Param("nome") String nome);
}
