package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.Data;

@Data
public class ContatoToUserDTO {
    private String email;
    private String numeroOuEmail;
    private String descricao;
}
