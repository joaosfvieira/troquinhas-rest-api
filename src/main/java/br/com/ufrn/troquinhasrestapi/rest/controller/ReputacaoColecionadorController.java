package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import br.com.ufrn.troquinhasrestapi.service.ReputacaoColecionadorService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/reputacao-colecionador")
public class ReputacaoColecionadorController {
    @Autowired
    ReputacaoColecionadorService reputacaoColecionadorService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    private List<ReputacaoColecionador> getAllReputacaoColecionador() {
        return reputacaoColecionadorService.getAllReputacaoColecionador();
    }

    @PostMapping("/colecionador/{idColecionador}")
    @ResponseStatus(CREATED)
    public ReputacaoColecionador save(@PathVariable Integer idColecionador, @RequestBody ReputacaoColecionador reputacaoColecionador){
        ReputacaoColecionador newReputacaoColecionador = ReputacaoColecionador.builder()
                .reputacao(reputacaoColecionador.getReputacao())
                .build();
        return reputacaoColecionadorService.addReputacaoColecionador(newReputacaoColecionador, idColecionador);
    }

    
    @GetMapping("{id}")
    public ResponseEntity<ReputacaoColecionador> getById(@PathVariable Integer id){
        Optional<ReputacaoColecionador> reputacaoColecionador = reputacaoColecionadorService.getReputacaoColecionadorById(id);
        return reputacaoColecionador.map(value ->
                ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update( @PathVariable Integer id, @RequestBody ReputacaoColecionador reputacaoColecionador ){
        ReputacaoColecionador newReputacaoColecionador = reputacaoColecionadorService.getReputacaoColecionadorById(id).orElseThrow();
        newReputacaoColecionador.setReputacao(reputacaoColecionador.getReputacao());
        reputacaoColecionadorService.atualizaReputacaoColecionador(newReputacaoColecionador);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        Colecionador colecionador = usuarioService.getColecionadorByReputacaoColecionador(reputacaoColecionadorService.getReputacaoColecionadorById(id).orElseThrow());
        colecionador.setReputacao(null);
        usuarioService.atualizaUsuario(colecionador);

        reputacaoColecionadorService.removeReputacaoColecionador(id);
    }
}