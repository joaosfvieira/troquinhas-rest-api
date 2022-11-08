package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FigurinhaDTO {
    @NotBlank(message = "Necessário informar nome da figurinha")
    private String nome;
    @NotBlank(message = "Necessário informar raridade")
    private String raridade;
}
