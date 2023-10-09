/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Security.Auth;

import Portofolio.LautaroRetta.Security.Entity.Role;
import Portofolio.LautaroRetta.Security.Entity.User;

import Portofolio.LautaroRetta.Security.Entity.UserRepository;
import Portofolio.LautaroRetta.Security.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
//con esta variable le pasamos a repositorio el usuario para que proceda a guardarlo o autenticarlo

    private final UserRepository userRepository;
    //es servicio se encarga de todo lo relacionado al token
    private final JwtService jwtService;
    //servicio para codificar la contraseña
    private final PasswordEncoder passwordEncoder;
    //servicio para autenticar usuario
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        //utilizamos el metodo autenticate, recibe el la contraseña y el ususrio
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //2°debemo traer un oibjeto UserDetails y utizamos
        UserDetails user = userRepository.findByUsername(request.getEmail()).orElseThrow();

        User userPrincipal = userRepository.findByUsername(request.getEmail()).orElseThrow();
        //generamos el token con el servicio jwt y el objeto user 
        String token = jwtService.getToken(user);
        //retornamos el token
        return AuthResponse.builder().token(token).username(userPrincipal.getUsername()).role(userPrincipal.getRole()).build();

    }

    public AuthResponse register(RegisterRequest request) {
        //Inicializamos un usuario con el patron de diseño builder
        // todos los datos los obtnemos del request
        //gracias a que creamos la clases RegisterRequest con las anotation de loombok podemos acceder a los getter y setters
        System.out.println(request.getUsername());
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(Role.User)
                .build();
        //le pasamos el usuario al repositor1590io para que lo guarde
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .username(user.getUsername())
                .role(user.getRole())
                .build();

    }

}
