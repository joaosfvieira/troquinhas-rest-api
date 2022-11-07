package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.repository.ContatoRepository;
import br.com.ufrn.troquinhasrestapi.repository.UsuarioRepository;

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

    @Autowired
    UsuarioRepository usuarioRepository;

    public Contato addContato(Contato c){ return contatoRepository.save(c); };

    public Optional<Contato> getContatoById(Long id){
        Optional<Contato> contato = contatoRepository.findById(id);
        return contato;
    };

    public List<Contato> getAllContatos(){ return contatoRepository.findAll(); }

    public void removeContato(Long id){ contatoRepository.deleteById(id); }

    public Contato atualizaContato(Contato c){ return contatoRepository.save(c); }

    /*
    public void addContatoToUser(String email, String contato, String descricao){
        Colecionador colecionador = usuarioRepository.findByEmail(email);
        Contato contato = roleRepository.findByName(roleName);
        colecionador.getRoles().add(role);
        userRepository.save(colecionador);
    };
    */
}