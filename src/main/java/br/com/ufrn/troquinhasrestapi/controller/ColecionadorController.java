package br.com.ufrn.troquinhasrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.service.ColecionadorService;

@RestController
public class ColecionadorController {
    
    @Autowired
    private ColecionadorService colecionadorService;

    @PostMapping("/addColecionador")
    public Colecionador addColecionador(@RequestBody Colecionador colecionador){
        return colecionadorService.addColecionador(colecionador);
    }
    
}
