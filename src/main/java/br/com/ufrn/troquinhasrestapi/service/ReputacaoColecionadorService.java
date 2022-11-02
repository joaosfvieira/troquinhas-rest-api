package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import br.com.ufrn.troquinhasrestapi.repository.ReputacaoColecionadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReputacaoColecionadorService {

    @Autowired
    ReputacaoColecionadorRepository reputacaoColecionadorRepository;

}