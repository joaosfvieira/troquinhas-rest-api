package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import br.com.ufrn.troquinhasrestapi.model.PontoTroca;
import br.com.ufrn.troquinhasrestapi.repository.FigurinhaRepository;
import br.com.ufrn.troquinhasrestapi.repository.PontoTrocaRepository;
import br.com.ufrn.troquinhasrestapi.repository.ColecionadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColecionadorService {
    @Autowired
    ColecionadorRepository colecionadorRepository;
    @Autowired
    PontoTrocaRepository pontoTrocaRepository;
    @Autowired
    FigurinhaRepository figurinhaRepository;

    public Colecionador addColecionador(Colecionador c){ return colecionadorRepository.save(c); };

    public Colecionador getColecionadorById(Integer id){
        return colecionadorRepository.findById(id).orElseThrow(() -> null);
    };

    public List<Colecionador> getAllColecionadores(){ return colecionadorRepository.findAll(); }

    public void removeColecionador(Integer id){ colecionadorRepository.deleteById(id); }

    public Colecionador atualizaColecionador(Colecionador c){ return colecionadorRepository.save(c); }

    public Figurinha adicionarFigurinhaAdquirida(Integer id, Integer idFigurinha) {
        Optional<Figurinha> f = figurinhaRepository.findById(idFigurinha);
        Optional<Colecionador> c = colecionadorRepository.findById(id);
        if(f.isPresent() && c.isPresent()) {
            c.get().getFigurinhasAdquiridas().add(f.get());
            colecionadorRepository.save(c.get());
            return f.get();
        }
        return null;
    }

    public Figurinha adicionarFigurinhaDesejada(Integer id, Integer idFigurinha) {
        Optional<Figurinha> f = figurinhaRepository.findById(idFigurinha);
        Optional<Colecionador> c = colecionadorRepository.findById(id);
        if(f.isPresent() && c.isPresent()) {
            c.get().getFigurinhasDesejadas().add(f.get());
            colecionadorRepository.save(c.get());
            return f.get();
        }
        return null;
    }

    public List<Colecionador> getAllColecionadoresWherePontoTrocaIdEqualsId(Integer id){
        return colecionadorRepository.getAllColecionadoresWherePontoTrocaIdEqualsId(id);
    }

    public Colecionador marcarPresenca(Integer id, Integer idPontoTroca) {
        Optional<Colecionador> c = colecionadorRepository.findById(id);
        Optional<PontoTroca> p = pontoTrocaRepository.findById(idPontoTroca);
        if(c.isPresent() && p.isPresent()){
            c.get().setPontoTroca(p.get());
            colecionadorRepository.save(c.get());
            return c.get();
        }
        return null;
    }
}