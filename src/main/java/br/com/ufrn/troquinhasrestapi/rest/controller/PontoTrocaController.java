package br.com.ufrn.troquinhasrestapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufrn.troquinhasrestapi.service.PontoTrocaService;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor @RequestMapping("/pontoTroca")
public class PontoTrocaController {

    @Autowired
    private PontoTrocaService pontoTrocaService;

}
