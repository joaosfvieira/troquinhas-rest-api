package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;

import br.com.ufrn.troquinhasrestapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ufrn.troquinhasrestapi.exception.ContatoNotFoundException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.rest.dto.ContatoToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.ContatoService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<List<Contato>> getAllContatos(){
        return ResponseEntity.ok().body(contatoService.getAllContatos());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Contato> saveContato(@Valid @RequestBody ContatoToUserDTO contatoToUserDTO){
        Contato toSaveContato = new Contato();

        Colecionador toSetColecionador = usuarioService.getUsuarioByEmail(contatoToUserDTO.getEmail());
        toSaveContato.setColecionador(toSetColecionador);

        toSaveContato.setDescricao(contatoToUserDTO.getDescricao());
        toSaveContato.setNumeroOuEmail(contatoToUserDTO.getNumeroOuEmail());

        contatoService.addContato(toSaveContato);
        toSetColecionador.setContato(toSaveContato);
        usuarioService.save(toSetColecionador);

        return ResponseEntity.ok().body(toSaveContato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> deleteContato(@PathVariable Integer id){
        Colecionador colecionador = usuarioService.getColecionadorByContato(contatoService.getContatoById(id).orElseThrow());
        colecionador.setContato(null);
        usuarioService.save(colecionador);
        contatoService.removeContato(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<Contato> getContato(@PathVariable Integer id){
        return ResponseEntity.ok().body(contatoService.getContatoById(id).orElseThrow(() -> new ContatoNotFoundException()));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @Valid @RequestBody Contato contato ){
        Contato contatoAtualizado = contatoService.getContatoById(id).orElseThrow();
        contatoAtualizado.setNumeroOuEmail(contato.getNumeroOuEmail());
        contatoAtualizado.setDescricao(contato.getDescricao());
        contatoService.atualizaContato(contatoAtualizado);
    }
    
}
