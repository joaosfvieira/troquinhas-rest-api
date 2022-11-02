package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import br.com.ufrn.troquinhasrestapi.model.PontoTroca;
import br.com.ufrn.troquinhasrestapi.repository.FigurinhaRepository;
import br.com.ufrn.troquinhasrestapi.repository.PontoTrocaRepository;
import br.com.ufrn.troquinhasrestapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PontoTrocaRepository pontoTrocaRepository;
    @Autowired
    FigurinhaRepository figurinhaRepository;

    public Colecionador addUsuario(Colecionador c){ return usuarioRepository.save(c); };

    public Colecionador getUsuarioById(Integer id){
        return usuarioRepository.findById(id).orElseThrow(() -> null);
    };

    public List<Colecionador> getAllUsuarios(){ return usuarioRepository.findAll(); }

    public void removeUsuario(Integer id){ usuarioRepository.deleteById(id); }

    public Colecionador atualizaUsuario(Colecionador c){ return usuarioRepository.save(c); }

    public Figurinha adicionarFigurinhaAdquirida(Integer id, Integer idFigurinha) {
        Optional<Figurinha> f = figurinhaRepository.findById(idFigurinha);
        Optional<Colecionador> c = usuarioRepository.findById(id);
        if(f.isPresent() && c.isPresent()) {
            c.get().getFigurinhasAdquiridas().add(f.get());
            usuarioRepository.save(c.get());
            return f.get();
        }
        return null;
    }

    public Figurinha adicionarFigurinhaDesejada(Integer id, Integer idFigurinha) {
        Optional<Figurinha> f = figurinhaRepository.findById(idFigurinha);
        Optional<Colecionador> c = usuarioRepository.findById(id);
        if(f.isPresent() && c.isPresent()) {
            c.get().getFigurinhasDesejadas().add(f.get());
            usuarioRepository.save(c.get());
            return f.get();
        }
        return null;
    }

    public List<Colecionador> getAllColecionadoresWherePontoTrocaIdEqualsId(Integer id){
        return usuarioRepository.getAllColecionadoresWherePontoTrocaIdEqualsId(id);
    }

    public Colecionador marcarPresenca(Integer id, Integer idPontoTroca) {
        Optional<Colecionador> c = usuarioRepository.findById(id);
        Optional<PontoTroca> p = pontoTrocaRepository.findById(idPontoTroca);
        if(c.isPresent() && p.isPresent()){
            c.get().setPontoTroca(p.get());
            usuarioRepository.save(c.get());
            return c.get();
        }
        return null;
    }
}