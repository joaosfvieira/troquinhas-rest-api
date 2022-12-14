package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Colecionador, Integer> {

    @Query("SELECT u FROM Colecionador u WHERE pontos_troca_id = :id")
    List<Colecionador> getAllColecionadoresWherePontoTrocaIdEqualsId(@Param("id") Integer id);

    @Query("SELECT u from Colecionador u WHERE email = :email")
    Colecionador findColecionadorByEmail(@Param("email") String email);

    Colecionador findByEmail(String email);

    @Query("UPDATE Colecionador set contato_id=null where id = :id")
    void deleteContatoById(@Param("id") Integer id);

    @Query("SELECT u FROM Colecionador u WHERE contato_id = :id")
    Colecionador getColecionadorByContato(@Param("id") Integer id);

    @Query("SELECT u FROM Colecionador u WHERE reputacao_colecionador_id = :id")
    Colecionador getColecionadorByReputacaoColecionador(@Param("id") Integer id);

    @Query("SELECT u FROM Colecionador u WHERE pontos_troca_id = :id")
    Colecionador getColecionadorByPontoTroca(@Param("id") Integer id);

//    @Query("DELETE FROM Colecionador.figurinhasAdquiridas b WHERE :idColecionador = colecionador_id AND :idFigurinha = figurinha_id")
//    public void removeFromColecionadorHasFigurinhasTable(@Param("idColecionador") Integer idColecionador,
//                                                         @Param("idFigurinha") Integer idFigurinha);

}