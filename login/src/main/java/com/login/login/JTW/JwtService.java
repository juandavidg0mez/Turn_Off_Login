package com.login.login.JTW;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String SECRET_KEY = "31321321313113132131313";

    public String getToken(UserDetails user) {
        // TODO Auto-generated method stub
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getkey(), SignatureAlgorithm.HS256)
            .compact();
    }
    private key getKey(){
        byte[] keybyte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybyte);
    }


}
