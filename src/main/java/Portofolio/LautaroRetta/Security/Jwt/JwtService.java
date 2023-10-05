/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portofolio.LautaroRetta.Security.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*ESTE SERVICIO SE ENCARGA DE TODO LO RELACIONADO A GENERAR EL TOKEN*/
@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        /*Un hashMap es una clase de colecciones que se utiliza
            para alamcenar pares de clave-valor, lo utilizamos en los claims 
        para pasar info adicional.
        Un claim son piezas de información sobre un usuario que se encuentran empaquetadas y firmadas con un token de seguridad.
        */
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
                 /*este metodo usa 1° parametro obj de tipo jey y el segundo el tipo de encriptacion,
               en este caso la encripctacion es hash 256 y utilizamos el metodo getKey()para traer una llave
               */
            .compact();
        
    }

    private Key getKey() {
       //pasamos nuestra secretKey a base 64 para mandarla como key a la firma del token
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);// usamos un array de byte y decodificamoes 
        return Keys.hmacShaKeyFor(keyByte); //nos permite crear una nueva instancia de la key
    }

    public String getUsernameFromToken(String token) {
        //con get calim le pasamos el token y claim subject donde almacenamos el username
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        //para validar un token debemos tambien saber si esta vencido, usamos el metodo getExpiracion
        
        //1° obtenemos el username del token
        final String username = getUsernameFromToken(token);
        //2°verificamos q username del token corresponde con el de userdetails y que no este expirado
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    
    //metodo que obtiene todos los claims de mi token, accdemos con la libreria JWTS
    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    //metodo generico que permite obtener un claims en particular
    //le pasamos como parametro una funcion claimsResolver
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        //aplicamos la funcion y retornamos el resultado
        //con eso podes ontener el user name
        return claimsResolver.apply(claims);
    }
    //obtenemos la fecha de expiracion del token
    private Date getExpiracion(String token){
        return getClaim(token, Claims::getExpiration);
    }
    //verificamos que no este expirado
    //con el metodo getExpiration ontenmos la fecha de exp del token 
    // con before comprara si la fecha es posterior al momento actual y da un boolean
    private boolean isTokenExpired(String token){
        return getExpiracion(token).before(new Date());
    }
    
}
