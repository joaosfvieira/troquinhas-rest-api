package br.com.ufrn.troquinhasrestapi.rest.dto;

import br.com.ufrn.troquinhasrestapi.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Data
@Builder
public class ColecionadorDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private PontoTroca pontoTroca;
    private Contato contato;
    private Collection<Role> roles = new ArrayList<>();
    private ReputacaoColecionador reputacao;
    private Set<Figurinha> figurinhasAdquiridas;
    private Set<Figurinha> figurinhasDesejadas;
}
