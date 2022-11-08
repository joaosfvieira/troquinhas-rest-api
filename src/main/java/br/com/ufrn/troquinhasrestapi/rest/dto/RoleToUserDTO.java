package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleToUserDTO {
    @NotBlank(message = "Necessário informar email do usuário")
    private String email;
    @NotBlank(message = "Necessário informar nome da role")
    private String roleName;
}
