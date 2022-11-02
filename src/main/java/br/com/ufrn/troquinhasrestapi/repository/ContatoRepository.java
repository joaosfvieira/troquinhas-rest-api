package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}