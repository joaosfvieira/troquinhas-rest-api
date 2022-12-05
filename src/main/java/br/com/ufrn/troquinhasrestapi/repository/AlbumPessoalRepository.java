package br.com.ufrn.troquinhasrestapi.repository;

import br.com.ufrn.troquinhasrestapi.model.AlbumPessoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumPessoalRepository extends JpaRepository<AlbumPessoal, Integer> {
}
