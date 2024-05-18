package com.muvit.MUVIT.infrastructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.muvit.MUVIT.domain.entities.Rol;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private final String SECRET_KEY = "Y29udHJhc2XDsWEgc3VwZXIgc2VjcmV0YSBxdWUgbmFkaWUgc2FiZSBzb2xvIHlvIHNl";

    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getToken(Map<String, Object> claims, Rol user){
        return  Jwts.builder()
        .claims(claims) // Agrego el cuerpo de jwt
        .subject(user.getNameUser()) // Agrego de quien es el jwt
        .issuedAt(new Date (System.currentTimeMillis())) // Fecha de creacion 
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Fecha de expiracion
        .signWith(this.getKey()) // Firmar el key
        .compact(); 
    }

    public String getToken(Rol user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId_rol());
        claims.put("role", user.getRolEnum().name());
        return getToken(claims, user);
    }

    public Claims getAllClaims(String token){

        return Jwts
        .parser()// Desarmar el jwt
        .verifyWith(this.getKey()) // Validamos que sea de la misma firma con la que la creo
        .build()// Lo construimos de nuevo
        .parseSignedClaims(token) // Convertimos el token a base 10
        .getPayload();// Extraemos el payload (claims)
    }
            // Metodo para obtener un claim en especifico
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = this.getAllClaims(token);
        return claimsResolver.apply(claims);
    }


    // Metodo para obtener el subject del jwt 
    public String getUserNameFromToken(String token){
        return this.getClaim(token, Claims::getSubject);
    }

    public Date getExpiration (String token){
       return this.getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return this.getExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails user){
        String userName = this.getUserNameFromToken(token);

        return userName.equals(user.getUsername()) && !this.isTokenExpired(token);
    }

}
