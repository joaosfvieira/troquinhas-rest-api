package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputacaoColecionadorRepository extends JpaRepository<ReputacaoColecionador, Integer>{
    
}
