package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    @NotBlank(message = "Necessário informar login")
    private String login;
    @NotBlank(message = "Necessário informar o token de autenticação")
    private String token;
}