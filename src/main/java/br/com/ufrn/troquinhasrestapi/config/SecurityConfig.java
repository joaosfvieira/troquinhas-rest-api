package br.com.ufrn.troquinhasrestapi.config;

import br.com.ufrn.troquinhasrestapi.security.JwtAuthFilter;
import br.com.ufrn.troquinhasrestapi.security.JwtService;
import br.com.ufrn.troquinhasrestapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioService usuarioService;
    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests((auth) -> {
                            try {
                                auth
                                        .antMatchers(HttpMethod.POST, "/colecionadores/auth")
                                        .permitAll()
                                        .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**")
                                        .permitAll()

                                        .antMatchers(HttpMethod.POST, "/ponto-trocas/**", "/roles/**", "/reputacao-colecionador/**")
                                        .hasRole("ADMIN")

                                        .antMatchers(HttpMethod.GET, "/roles/**")
                                        .hasAnyRole("ADMIN", "DEVELOPER")
                                        .antMatchers(HttpMethod.POST, "/figurinhas/**")
                                        .hasAnyRole("ADMIN", "DEVELOPER")

                                        .antMatchers(HttpMethod.GET, "/**")
                                        .authenticated()
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
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
