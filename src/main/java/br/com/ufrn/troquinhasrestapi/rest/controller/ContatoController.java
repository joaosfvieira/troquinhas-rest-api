package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufrn.troquinhasrestapi.exception.ContatoNotFoundException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.rest.dto.ContatoToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.ContatoService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor @RequestMapping("/contato")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContatos(){
        return ResponseEntity.ok().body(contatoService.getAllContatos());
    }

    @PostMapping("/save")
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

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteContato(@PathVariable Long id){
        contatoService.removeContato(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getContato/{id}")
    public ResponseEntity<Contato> getContato(@PathVariable long id){
        return ResponseEntity.ok().body(contatoService.getContatoById(id).orElseThrow(() -> new ContatoNotFoundException()));
    }
}
