package br.com.ufrn.troquinhasrestapi.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Service;

import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Role;
import br.com.ufrn.troquinhasrestapi.repository.RoleRepository;
import br.com.ufrn.troquinhasrestapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class RoleService {

    private final UsuarioRepository userRepository;
    private final RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String email, String roleName){
        Colecionador colecionador = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        colecionador.getRoles().add(role);
        userRepository.save(colecionador);
    }

    public void delete(Integer id) throws NameNotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NameNotFoundException("Role não encontrada"));
        roleRepository.delete(role);
    }

    public Role getRoleById(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new Error("Role não encontrada"));
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
