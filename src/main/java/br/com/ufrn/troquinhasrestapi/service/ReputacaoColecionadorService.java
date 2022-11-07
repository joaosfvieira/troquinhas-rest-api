package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import br.com.ufrn.troquinhasrestapi.repository.ReputacaoColecionadorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReputacaoColecionadorService {

    @Autowired
    ReputacaoColecionadorRepository reputacaoColecionadorRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<ReputacaoColecionador> getAllReputacaoColecionador() {
        return reputacaoColecionadorRepository.findAll();
    }

    public ReputacaoColecionador addReputacaoColecionador(ReputacaoColecionador reputacaoColecionador, Integer idColecionador) {
        Colecionador colecionador = usuarioService.getUsuarioById(idColecionador);
        ReputacaoColecionador newReputacaoColecionador = reputacaoColecionadorRepository.save(reputacaoColecionador);
        colecionador.setReputacao(newReputacaoColecionador);
        usuarioService.atualizaUsuario(colecionador);
        return newReputacaoColecionador;
    }

    public Optional<ReputacaoColecionador> getReputacaoColecionadorById(Integer id) {
        return reputacaoColecionadorRepository.findById(id);
    }

    public ReputacaoColecionador atualizaReputacaoColecionador(ReputacaoColecionador reputacaoColecionador){
        return reputacaoColecionadorRepository.save(reputacaoColecionador);
    }

    public void removeReputacaoColecionador(Integer id) {
        reputacaoColecionadorRepository.deleteById(id);
    }
}