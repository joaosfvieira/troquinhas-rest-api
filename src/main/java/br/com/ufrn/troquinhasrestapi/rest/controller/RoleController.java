package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.net.URI;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ufrn.troquinhasrestapi.model.Role;
import br.com.ufrn.troquinhasrestapi.rest.dto.RoleToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.RoleService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor @RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping()
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role rolex = roleService.saveRole(role);
        return ResponseEntity.ok().body(rolex);
    }

    @PostMapping("/colecionador")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO){
        roleService.addRoleToUser(roleToUserDTO.getEmail(), roleToUserDTO.getRoleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) throws NameNotFoundException{
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRole(@PathVariable long id){
        return ResponseEntity.ok().body(roleService.getRole(id));
    }
}
