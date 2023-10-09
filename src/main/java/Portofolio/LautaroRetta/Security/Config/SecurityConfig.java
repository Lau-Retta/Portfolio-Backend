/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Security.Config;

import Portofolio.LautaroRetta.Security.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    //metodo que tiene cadena de filtros que se ejecutan para filtrar el acceso
    @Bean
    //consiguramos los endpoint publicos de los que estan proitegidos
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and() // Habilita la configuración CORS
                .csrf().disable() // Deshabilita CSRF
                //la proteccion Cross-Site Request Forgery es una medida de seguridad se utiliza para agregar
                //a los peticiones post una autenticacion bsada en un token csrf, pero como vamos a usar un token jwt lo desactivamos
                .authorizeHttpRequests(authRequest
                        -> authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        // le digo que todos lo que vengan con la ruta auth se permitan porque son publicos
                        .anyRequest().authenticated()//aqui le digo que cualquier otro se debe autenticar
                )
                //como trabajamos con una autenticacion basada en jwt, lo que requerimos es:
                //inabilitamos las sesiones 
                .sessionManagement(sessionManager
                        -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //especificamos el provider
                .authenticationProvider(authProvider)
                //agregamos el filtro de jwtAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200/")
                        .allowedMethods()
                        .maxAge(3600);
            }

        };
    }
}
