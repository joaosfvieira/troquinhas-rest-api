package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.net.URI;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ufrn.troquinhasrestapi.model.Role;
import br.com.ufrn.troquinhasrestapi.rest.dto.RoleToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.RoleService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor @RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        System.out.println("\n\nTESTANDO SAVEROLE... \n\n");
        Role rolex = roleService.saveRole(role);
        return ResponseEntity.ok().body(rolex);
    }

    @PostMapping("/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO){
        roleService.addRoleToUser(roleToUserDTO.getEmail(), roleToUserDTO.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) throws NameNotFoundException{
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getRole/{id}")
    public ResponseEntity<Role> getRole(@PathVariable long id){
        return ResponseEntity.ok().body(roleService.getRole(id));
    }
}
