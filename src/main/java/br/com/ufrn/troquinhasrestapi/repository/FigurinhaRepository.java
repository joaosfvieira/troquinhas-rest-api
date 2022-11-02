package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigurinhaRepository extends JpaRepository<Figurinha, Integer> {
}