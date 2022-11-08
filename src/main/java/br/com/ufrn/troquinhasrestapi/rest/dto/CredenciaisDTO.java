package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CredenciaisDTO {
    @NotBlank(message = "Necessário informar login")
    private String login;
    @NotBlank(message = "Necessário informar senha")
    private String senha;
}

