package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.AlbumTipo;
import br.com.ufrn.troquinhasrestapi.repository.AlbumTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumTipoService {
    @Autowired
    private AlbumTipoRepository albumTipoRepository;

    public Optional<AlbumTipo> getAlbumTipoById(Integer id) {
        return albumTipoRepository.findById(id);
    }


}
