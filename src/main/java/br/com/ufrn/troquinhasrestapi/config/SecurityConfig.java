package br.com.ufrn.troquinhasrestapi.config;

import br.com.ufrn.troquinhasrestapi.security.JwtAuthFilter;
import br.com.ufrn.troquinhasrestapi.security.JwtService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> {
                            try {
                                auth
                                        .antMatchers("/contatos/**", "/figurinhas/**", "/contatos/**")
                                        .hasAnyRole("USER","ADMIN")
                                        .antMatchers("/pontotrocas/**")
                                        .hasRole("ADMIN")
                                        .antMatchers(HttpMethod.POST, "/colecionadores/**")
                                        .permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
        return http.build();
    }
}
