package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    public Contato addContato(Contato c){ return contatoRepository.save(c); };

    public Optional<Contato> getContatoById(Integer id){
        Optional<Contato> contato = contatoRepository.findById(id);
        return contato;
    };

    public List<Contato> getAllContatos(){ return contatoRepository.findAll(); }

    public void removeContato(Integer id){ contatoRepository.deleteById(id); }

    public Contato atualizaContato(Contato c){ return contatoRepository.save(c); }
}