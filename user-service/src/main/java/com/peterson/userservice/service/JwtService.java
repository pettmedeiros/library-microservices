package com.peterson.userservice.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Lê a chave do application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Tempo de expiração (24 horas)
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    // Converte a String da chave em uma chave criptográfica
    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //Gera o token 
    public String generateToken(String email){

        Date now = new Date();

        Date expiration = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now) // Data em que o token foi criado
                .expiration(expiration)  // Data em que o token deixará de ser válido
                .signWith(getSigningKey()) // Assina o token utilizando nossa chave secreta.
                .compact();  // Converte tudo para uma String JWT
    }

    // EXTRAI O E-MAIL DO TOKEN
    public String extractEmail(String token){

        return Jwts.parser()
                .verifyWith(getSigningKey())  // Usa a mesma chave utilizada para gerar o token.
                .build()
                .parseSignedClaims(token)  // Lê e valida o token recebido
                .getPayload() // Obtém o conteúdo (payload) do token
                .getSubject();// Retorna o Subject.
    }

     // VALIDA O TOKEN
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);

            return true; // Se chegou aqui, o token é válido.
        } catch (Exception e) {
            return false;
        }
    }

}
