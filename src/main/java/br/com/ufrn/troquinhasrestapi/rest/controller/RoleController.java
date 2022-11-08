package br.com.ufrn.troquinhasrestapi.rest.controller;

import java.util.List;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ufrn.troquinhasrestapi.model.Role;
import br.com.ufrn.troquinhasrestapi.rest.dto.RoleToUserDTO;
import br.com.ufrn.troquinhasrestapi.service.RoleService;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role rolex = roleService.saveRole(role);
        return ResponseEntity.ok().body(rolex);
    }

    @PostMapping("/colecionador")
    @ResponseStatus(OK)
    public ResponseEntity<?> addRoleToUser(@Valid @RequestBody RoleToUserDTO roleToUserDTO){
        roleService.addRoleToUser(roleToUserDTO.getEmail(), roleToUserDTO.getRoleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> deleteRole(@PathVariable Integer id) throws NameNotFoundException{
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<Role> getRole(@PathVariable Integer id){
        return ResponseEntity.ok().body(roleService.getRoleById(id));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Role role ){
        Role roleAtualizado = roleService.getRoleById(id);
        roleAtualizado.setName(role.getName());
        roleService.saveRole(roleAtualizado);
    }
}
