package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.AlbumPessoal;
import br.com.ufrn.troquinhasrestapi.repository.AlbumPessoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumPessoalService {
    @Autowired
    private AlbumPessoalRepository albumPessoalRepository;

    public Optional<AlbumPessoal> getAlbumPessoalById(Integer idAlbumPessoal) {
        return albumPessoalRepository.findById(idAlbumPessoal);
    }

    public AlbumPessoal save(AlbumPessoal albumPessoal) {
        return albumPessoalRepository.save(albumPessoal);
    }
}
