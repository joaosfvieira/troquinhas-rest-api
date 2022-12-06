package br.com.ufrn.troquinhasrestapi.service;

import br.com.ufrn.troquinhasrestapi.exception.SenhaInvalidaException;
import br.com.ufrn.troquinhasrestapi.model.*;
import br.com.ufrn.troquinhasrestapi.repository.UsuarioRepository;
import br.com.ufrn.troquinhasrestapi.rest.dto.ColecionadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PontoTrocaService pontoTrocaService;
    @Autowired
    FigurinhaService figurinhaService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AlbumPessoalService albumPessoalService;
    @Autowired
    AlbumTipoService albumTipoService;

    public Colecionador save(Colecionador c){ return usuarioRepository.save(c); };

    public Optional<Colecionador> getUsuarioById(Integer id){
        return usuarioRepository.findById(id);
    };

    public List<Colecionador> getAllUsuarios(){ return usuarioRepository.findAll(); }

    public void removeUsuario(Integer id){ usuarioRepository.deleteById(id); }

    public ColecionadorDTO adicionarFigurinhaAdquirida(Integer idColecionador, Integer idAlbumPessoal, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        AlbumPessoal albumPessoal = albumPessoalService.getAlbumPessoalById(idAlbumPessoal).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        albumPessoal.getFigurinhasAdquiridas().add(figurinha);
        save(colecionador);
        return converteColecionadorParaDTO(colecionador);
    }

    public ColecionadorDTO adicionarFigurinhaDesejada(Integer idColecionador, Integer idAlbumPessoal, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        AlbumPessoal albumPessoal = albumPessoalService.getAlbumPessoalById(idAlbumPessoal).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        albumPessoal.getFigurinhasDesejadas().add(figurinha);
        save(colecionador);
        return converteColecionadorParaDTO(colecionador);
    }

    public List<ColecionadorDTO> getColecionadoresEmPontoTroca(Integer id){
        List<Colecionador> colecionadores = usuarioRepository.getAllColecionadoresWherePontoTrocaIdEqualsId(id);
        List<ColecionadorDTO> colecionadoresDTO = new ArrayList<>();
        for (Colecionador c: colecionadores)
            colecionadoresDTO.add(converteColecionadorParaDTO(c));
        return colecionadoresDTO;
    }

    public ColecionadorDTO marcarPresenca(Integer idColecionador, Integer idPontoTroca) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        PontoTroca pontoTroca = pontoTrocaService.getPontoTrocaById(idPontoTroca).orElseThrow();
        colecionador.setPontoTroca(pontoTroca);
        save(colecionador);
        return converteColecionadorParaDTO(colecionador);
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
        Collection<Role> userRoles = colecionador.getRoles();
        String[] roles = null;
        
        for (Role role : userRoles) {
            if(Objects.equals(role.getName(), "Admin")){
                roles =  new String[] { "ADMIN", "USER" };
            } else if (Objects.equals(role.getName(), "Developer")){
                roles =  new String[] {"USER" };
            } else if (Objects.equals(role.getName(), "User")){
                roles = new String[] {"DEVELOPER"};
            }
        }
        
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
                .reputacao(c.getReputacaoColecionador())
                .albunsPessoais(c.getAlbunsPessoais())
                .build();
    }


    public ColecionadorDTO removerFigurinhaAdquirida(Integer idColecionador, Integer idAlbumPessoal, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        AlbumPessoal albumPessoal = albumPessoalService.getAlbumPessoalById(idAlbumPessoal).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        albumPessoal.getFigurinhasAdquiridas().remove(figurinha);
        save(colecionador);
        return converteColecionadorParaDTO(colecionador);
    }

    public ColecionadorDTO removerFigurinhaDesejada(Integer idColecionador, Integer idAlbumPessoal, Integer idFigurinha) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        AlbumPessoal albumPessoal = albumPessoalService.getAlbumPessoalById(idAlbumPessoal).orElseThrow();
        Figurinha figurinha = figurinhaService.getFigurinhaById(idFigurinha).orElseThrow();
        albumPessoal.getFigurinhasDesejadas().remove(figurinha);
        save(colecionador);
        return converteColecionadorParaDTO(colecionador);
    }

    public AlbumPessoal adicionarAlbumPessoal(Integer idColecionador, Integer idAlbumTipo) {
        Colecionador colecionador = getUsuarioById(idColecionador).orElseThrow();
        AlbumTipo albumTipo = albumTipoService.getAlbumTipoById(idAlbumTipo).orElseThrow();
        AlbumPessoal albumPessoal =  AlbumPessoal.builder()
                                        .colecionador(colecionador)
                                        .albumTipo(albumTipo)
                                        .build();
        return albumPessoalService.save(albumPessoal);
    }
}