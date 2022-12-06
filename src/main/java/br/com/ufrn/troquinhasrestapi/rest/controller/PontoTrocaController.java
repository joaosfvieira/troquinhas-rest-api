package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PreRemove;

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

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.PontoTroca;
import br.com.ufrn.troquinhasrestapi.service.PontoTrocaService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ponto-trocas")
public class PontoTrocaController {

    @Autowired
    private PontoTrocaService pontoTrocaService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(OK)
    private List<PontoTroca> getAllPontoTroca() {
        return pontoTrocaService.getAllPontoTroca();
    }
    
    @PostMapping
    @ResponseStatus(CREATED)
    public PontoTroca save(@RequestBody PontoTroca pontoTroca){
        return pontoTrocaService.addPontoTroca(pontoTroca);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<PontoTroca> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(pontoTrocaService.getPontoTrocaById(id).orElseThrow());
    }
    
    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody PontoTroca pontoTroca ){
        PontoTroca pontoTrocaAtualizado = pontoTrocaService.getPontoTrocaById(id).orElseThrow();
        pontoTrocaAtualizado.setNome(pontoTroca.getNome());
        pontoTrocaService.atualizaPontoTroca(pontoTrocaAtualizado);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Optional<PontoTroca> pontoTroca = pontoTrocaService.getPontoTrocaById(id);

        PreRemove(pontoTroca);

        pontoTrocaService.removePontoTroca(id);
    }

    @PreRemove
    private void PreRemove(Optional<PontoTroca> pontoTroca) {
        List<Colecionador> colecionadores = usuarioService.getAllUsuarios();
        for(Colecionador c : colecionadores) {
            if(c.getPontoTroca() == pontoTroca.get()){
                c.setPontoTroca(null);
                usuarioService.save(c);
            }
        }
    }
}
