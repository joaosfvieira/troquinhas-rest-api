package br.com.ufrn.troquinhasrestapi.rest.controller;

import br.com.ufrn.troquinhasrestapi.exception.SenhaInvalidaException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.rest.dto.ColecionadorDTO;
import br.com.ufrn.troquinhasrestapi.rest.dto.CreateUserDTO;
import br.com.ufrn.troquinhasrestapi.rest.dto.CredenciaisDTO;
import br.com.ufrn.troquinhasrestapi.rest.dto.TokenDTO;
import br.com.ufrn.troquinhasrestapi.security.JwtService;
import br.com.ufrn.troquinhasrestapi.service.RoleService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/colecionadores")
@RequiredArgsConstructor
public class ColecionadorController {
    private final UsuarioService usuarioService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @GetMapping
    public List<Colecionador> getColecionadores(){
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColecionadorDTO salvar(@RequestBody CreateUserDTO createUserDTO) throws RoleNotFoundException{
        String senhaCriptografada = passwordEncoder.encode(createUserDTO.getSenha());
        createUserDTO.setSenha(senhaCriptografada);
        
        Colecionador colecionador = new Colecionador();
        colecionador.setNome(createUserDTO.getNome());
        colecionador.setEmail(createUserDTO.getEmail());
        colecionador.setSenha(senhaCriptografada);
        colecionador.setSobrenome(createUserDTO.getSobrenome());
        
        if(createUserDTO.getContato()!=null){
            String[] strContato = createUserDTO.getContato();
            Contato contato = new Contato();
            contato.setColecionador(colecionador);
            contato.setNumeroOuEmail(strContato[0]);
            contato.setDescricao(strContato[1]);
    
            colecionador.setContato(contato);
        }
        
        colecionador = usuarioService.save(colecionador);
        
        for (String role : createUserDTO.getRoles()) {
            roleService.addRoleToUser(colecionador.getEmail(), role);
        }

        return usuarioService.converteColecionadorParaDTO(colecionador);
    }

    @DeleteMapping("/{id}")
    public void deleteColecionador(@PathVariable Integer id){
        usuarioService.removeUsuario(id);
    }

    @GetMapping("/{id}")
    public Colecionador getColecionador(@PathVariable Integer id){
        return usuarioService.getUsuarioById(id).orElseThrow();
    }

    /*
    @PutMapping("/{id}")
    public Colecionador updateColecionador(@RequestBody CreateUserDTO createUserDTO, @PathVariable Integer id) throws Exception{
        Colecionador colecionadorA = usuarioService.getUsuarioById(id);
        if(createUserDTO != null){
            if(createUserDTO.getEmail() == null || createUserDTO.getSenha() == null){
                throw new Exception("ERROU AI PAI");
            }
            usuarioService.atualizaUsuario(colecionadorA);
        }
        return colecionadorA;
    }
    */

    @PutMapping("/{id}")
    public ColecionadorDTO update(@PathVariable Integer id, @RequestBody Colecionador colecionador){
        Colecionador c = usuarioService.getUsuarioById(id).orElseThrow();
        c = colecionador;
        usuarioService.save(c);
        return usuarioService.converteColecionadorParaDTO(c);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Colecionador colecionador = Colecionador.builder()
                    .email(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(colecionador);
            String token = jwtService.gerarToken(colecionador);
            return new TokenDTO(colecionador.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{idColecionador}/has-figurinha/{idFigurinha}")
    public ColecionadorDTO adicionarFigurinhaAdquirida(@PathVariable Integer idColecionador, @PathVariable Integer idFigurinha) {
        return usuarioService.adicionarFigurinhaAdquirida(idColecionador, idFigurinha);
    }

    @GetMapping("/{idColecionador}/wants-figurinha/{idFigurinha}")
    public ColecionadorDTO adicionarFigurinhaDesejada(@PathVariable Integer idColecionador, @PathVariable Integer idFigurinha) {
        return usuarioService.adicionarFigurinhaDesejada(idColecionador, idFigurinha);
    }

    @GetMapping("/{idColecionador}/ponto-trocas/{idPontoTroca}")
    public ColecionadorDTO adicionarPontoTrocaParaColecionador(@PathVariable Integer idColecionador, @PathVariable Integer idPontoTroca) {
        return usuarioService.marcarPresenca(idColecionador, idPontoTroca);
    }

    @GetMapping("/ponto-trocas/{idPontoTroca}")
    public List<ColecionadorDTO> listColecionadorPontoTroca(@PathVariable Integer idPontoTroca) {
        return usuarioService.getColecionadoresEmPontoTroca(idPontoTroca);
    }


}

