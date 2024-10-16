package br.com.fiap.coletaverde.repository;

import br.com.fiap.coletaverde.model.Morador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MoradorRepository
        extends CrudRepository<Morador, Long> {
    @Query("SELECT a FROM Morador a WHERE a.nome = :nome")
    Optional<Morador> buscarPorNome(@Param("nome") String nome);
    UserDetails findByEmail(String email);
}

