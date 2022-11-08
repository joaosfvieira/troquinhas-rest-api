package br.com.ufrn.troquinhasrestapi.rest.dto;

import java.util.ArrayList;

import antlr.collections.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "Necessário informar nome do usuário")
    String nome;
    @NotBlank(message = "Necessário informar sobrenome do usuário")
    String sobrenome;
    @NotBlank(message = "Necessário informar email do usuário")
    String email;
    @NotBlank(message = "Necessário informar senha")
    String senha;
    String [] roles;
    String [] contato;
}
