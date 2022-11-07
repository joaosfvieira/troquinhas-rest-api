package br.com.ufrn.troquinhasrestapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import br.com.ufrn.troquinhasrestapi.TroquinhasRestApiApplication;
import br.com.ufrn.troquinhasrestapi.model.Colecionador;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    

    public String gerarToken( Colecionador colecionador ){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(chaveAssinatura));

        return "rafa";
        // return Jwts
        //         .builder()
        //         .setSubject(colecionador.getEmail())
        //         .setExpiration(data)
        //         .signWith(key)
        //         .compact();
    }

    

     private Claims obterClaims( String token ) throws ExpiredJwtException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(chaveAssinatura));
        return Jwts
                 .parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
    }
     
    public boolean tokenValido( String token ){
        try{
            Claims claims = (Claims) obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }
    

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) (obterClaims(token)).getSubject();
    }
}
