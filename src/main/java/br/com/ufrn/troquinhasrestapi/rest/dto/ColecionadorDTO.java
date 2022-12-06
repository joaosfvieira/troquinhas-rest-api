package br.com.ufrn.troquinhasrestapi.rest.dto;

import br.com.ufrn.troquinhasrestapi.model.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ColecionadorDTO {
    @NotBlank(message = "Necessário informar nome do usuário")
    private String nome;
    @NotBlank(message = "Necessário informar sobrenome do usuário")
    private String sobrenome;
    @NotBlank(message = "Necessário informar email do usuário")
    private String email;
    private PontoTroca pontoTroca;
    private Contato contato;
    private Collection<Role> roles;
    private ReputacaoColecionador reputacao;
//    private Set<Figurinha> figurinhasAdquiridas;
//    private Set<Figurinha> figurinhasDesejadas;
    private Set<AlbumPessoal> albunsPessoais;
}
