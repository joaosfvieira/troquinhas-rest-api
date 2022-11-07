package br.com.ufrn.troquinhasrestapi.rest.dto;

import lombok.Data;

@Data
public class RoleToUserDTO {
    private String email;
    private String roleName;
}
