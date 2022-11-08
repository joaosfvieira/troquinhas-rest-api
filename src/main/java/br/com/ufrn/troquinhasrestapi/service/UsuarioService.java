package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.exception.SenhaInvalidaException;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;
import br.com.ufrn.troquinhasrestapi.model.Contato;
import br.com.ufrn.troquinhasrestapi.model.Figurinha;
import br.com.ufrn.troquinhasrestapi.model.PontoTroca;
import br.com.ufrn.troquinhasrestapi.model.ReputacaoColecionador;
import br.com.ufrn.troquinhasrestapi.model.Role;
import br.com.ufrn.troquinhasrestapi.repository.FigurinhaRepository;
import br.com.ufrn.troquinhasrestapi.repository.PontoTrocaRepository;
import br.com.ufrn.troquinhasrestapi.repository.UsuarioRepository;
import br.com.ufrn.troquinhasrestapi.rest.dto.ColecionadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PontoTrocaRepository pontoTrocaRepository;
    @Autowired
    FigurinhaService figurinhaService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Colecionador addUsuario(Colecionador c){ return usuarioRepository.save(c); };

    public Optional<Colecionador> getUsuarioById(Integer id){
        return usuarioRepository.findById(id);
    };

    public List<Colecionador> getAllUsuarios(){ return usuarioRepository.findAll(); }

    public void removeUsuario(Integer id){ usuarioRepository.deleteById(id); }

    public Colecionador atualizaUsuario(Colecionador c){ return usuarioRepository.save(c); }

    public ColecionadorDTO adicionarFigurinhaAdquirida(Integer idColecionador, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        colecionador.getFigurinhasAdquiridas().add(figurinha);
        return converteColecionadorParaDTO(colecionador);
    }

    public ColecionadorDTO adicionarFigurinhaDesejada(Integer idColecionador, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        colecionador.getFigurinhasDesejadas().add(figurinha);
        atualizaUsuario(colecionador);
        return converteColecionadorParaDTO(colecionador);
    }

    public List<Colecionador> getAllColecionadoresWherePontoTrocaIdEqualsId(Integer id){
        return usuarioRepository.getAllColecionadoresWherePontoTrocaIdEqualsId(id);
    }

    public Colecionador marcarPresenca(Integer id, Integer idPontoTroca) {
        Optional<Colecionador> c = usuarioRepository.findById(id);
        Optional<PontoTroca> p = pontoTrocaRepository.findById(idPontoTroca);
        if(c.isPresent() && p.isPresent()){
            c.get().setPontoTroca(p.get());
            usuarioRepository.save(c.get());
            return c.get();
        }
        return null;
    }

    public UserDetails autenticar(Colecionador colecionador ){
        UserDetails user = loadUserByUsername(colecionador.getEmail());
        boolean senhasBatem = passwordEncoder.matches( colecionador.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Colecionador colecionador = usuarioRepository.findColecionadorByEmail(username);

                //.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
        Collection<Role> userRoles = colecionador.getRoles();

        String[] roles = null;
        System.out.println(" !!!!!!!  roles = null  !!!!!!!!!!!!!!");
        for (Role role : userRoles) {
            if(Objects.equals(role.getName(), "Admin")){
                roles =  new String[] { "ADMIN", "USER" };
                System.out.println(" ROLES = ADMIN USER");
            }else{
                roles =  new String[] {"USER" };
                System.out.println(" ROLES = ADMIN USER");
            }
        }

        //String[] roles =  new String[] { "ADMIN", "USER" } : new String[] { "USER" };

        return User
                .builder()
                .username(colecionador.getEmail())
                .password(colecionador.getSenha())
                .roles(roles)
                .build();
    }

    public Colecionador getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void deleteContatoById(Integer id){
        usuarioRepository.deleteContatoById(id);
    }

    public Colecionador getColecionadorByContato(Contato c){
        return usuarioRepository.getColecionadorByContato(c.getId());
    }

    public Colecionador getColecionadorByReputacaoColecionador(ReputacaoColecionador reputacaoColecionador) {
        return usuarioRepository.getColecionadorByReputacaoColecionador(reputacaoColecionador.getId());
    }

    public Colecionador getColecionadorByPontoTroca(PontoTroca pontoTroca) {
        return usuarioRepository.getColecionadorByPontoTroca(pontoTroca.getId());
    }

    public ColecionadorDTO converteColecionadorParaDTO(Colecionador c) {
        return ColecionadorDTO.builder()
                .nome(c.getNome())
                .sobrenome(c.getSobrenome())
                .email(c.getEmail())
                .pontoTroca(c.getPontoTroca())
                .contato(c.getContato())
                .roles(c.getRoles())
                .reputacao(c.getReputacao())
                .figurinhasAdquiridas(c.getFigurinhasAdquiridas())
                .figurinhasDesejadas(c.getFigurinhasDesejadas())
                .build();
    }


}