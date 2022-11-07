package br.com.ufrn.troquinhasrestapi.rest.dto;

import java.util.ArrayList;

import antlr.collections.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDTO {
    String nome;
    String sobrenome;
    String email;
    String senha;
    String [] roles;
    String [] contato;
}
