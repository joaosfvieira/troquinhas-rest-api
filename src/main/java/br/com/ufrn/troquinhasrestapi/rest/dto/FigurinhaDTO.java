package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FigurinhaDTO {
    private String nome;
    private String raridade;
}
