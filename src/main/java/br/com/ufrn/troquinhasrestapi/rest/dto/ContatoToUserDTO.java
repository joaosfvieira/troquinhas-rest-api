package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContatoToUserDTO {
    @NotBlank(message = "Necessário informar email do usuário")
    private String email;
    @NotBlank(message = "Necessário informar contato")
    private String numeroOuEmail;
    @NotBlank(message = "Necessário informar descrição")
    private String descricao;
}
