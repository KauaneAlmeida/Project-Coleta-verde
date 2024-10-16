package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.Rota;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RotaRepository
        extends CrudRepository<Rota,Long> {
    @Query("SELECT a FROM Rota a WHERE a.nome = :nome")
    Optional<Rota> buscarPorNome(@Param("nome") String nome);
}
