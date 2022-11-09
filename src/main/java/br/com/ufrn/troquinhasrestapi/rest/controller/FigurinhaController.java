package br.com.ufrn.troquinhasrestapi.rest.controller;

import br.com.ufrn.troquinhasrestapi.exception.SenhaInvalidaException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import br.com.ufrn.troquinhasrestapi.model.PontoTroca;
import br.com.ufrn.troquinhasrestapi.rest.dto.FigurinhaDTO;
import br.com.ufrn.troquinhasrestapi.service.FigurinhaService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PreRemove;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/figurinhas")
public class FigurinhaController {
    @Autowired
    FigurinhaService figurinhaService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(OK)
    private List<Figurinha> list() {
        return figurinhaService.getAllFigurinhas();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Figurinha save(@Valid @RequestBody FigurinhaDTO figurinhaDTO){
        try{
            Figurinha figurinha = Figurinha.builder()
                    .nome(figurinhaDTO.getNome())
                    .raridade(figurinhaDTO.getRaridade())
                    .build();
            return figurinhaService.addFigurinha(figurinha);
        } catch (Exception e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<Figurinha> getById(@PathVariable Integer id){
        Optional<Figurinha> figurinha = figurinhaService.getFigurinhaById(id);
        return figurinha.map(value ->
                ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Figurinha figurinha ){
        figurinhaService
                .getFigurinhaById(id)
                .map( f -> {
                    figurinha.setId(f.getId());
                    figurinhaService.addFigurinha(figurinha);
                    return figurinha;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Figurinha não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Figurinha figurinha = figurinhaService.getFigurinhaById(id).orElseThrow();
        PreRemove(figurinha);
        figurinhaService.removeFigurinha(id);
    }

    @PreRemove
    private void PreRemove(Figurinha figurinha) {
        List<Colecionador> colecionadores = usuarioService.getAllUsuarios();
        for(Colecionador c : colecionadores) {
            if(c.getFigurinhasAdquiridas().contains(figurinha)){
                c.getFigurinhasAdquiridas().remove(figurinha);
                usuarioService.save(c);
            }
            if(c.getFigurinhasDesejadas().contains(figurinha)){
                c.getFigurinhasDesejadas().remove(figurinha);
                usuarioService.save(c);
            }
        }
    }
}
