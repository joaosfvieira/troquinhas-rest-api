package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Colecionador, Integer> {

    @Query("SELECT u FROM Colecionador u WHERE pontos_troca_id = :id")
    public List<Colecionador> getAllColecionadoresWherePontoTrocaIdEqualsId(@Param("id") Integer id);

    @Query("SELECT u from Colecionador u WHERE email = :email")
    public Colecionador findColecionadorByEmail(@Param("email") String email);

    public Colecionador findByEmail(String email);
}