package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ufrn.troquinhasrestapi.exception.ContatoNotFoundException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.rest.dto.ContatoToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.ContatoService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContatos(){
        return ResponseEntity.ok().body(contatoService.getAllContatos());
    }

    @PostMapping
    public ResponseEntity<Contato> saveContato(@RequestBody ContatoToUserDTO contatoToUserDTO){
        Contato toSaveContato = new Contato();

        Colecionador toSetColecionador = usuarioService.getUsuarioByEmail(contatoToUserDTO.getEmail());
        toSaveContato.setColecionador(toSetColecionador);

        toSaveContato.setDescricao(contatoToUserDTO.getDescricao());
        toSaveContato.setNumeroOuEmail(contatoToUserDTO.getNumeroOuEmail());

        contatoService.addContato(toSaveContato);
        toSetColecionador.setContato(toSaveContato);
        usuarioService.atualizaUsuario(toSetColecionador);

        return ResponseEntity.ok().body(toSaveContato);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteContato(@PathVariable Integer id){
        Colecionador colecionador = usuarioService.getColecionadorByContato(contatoService.getContatoById(id).orElseThrow());
        colecionador.setContato(null);
        usuarioService.atualizaUsuario(colecionador);
        contatoService.removeContato(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Contato> getContato(@PathVariable Integer id){
        return ResponseEntity.ok().body(contatoService.getContatoById(id).orElseThrow(() -> new ContatoNotFoundException()));
    }

    
}
