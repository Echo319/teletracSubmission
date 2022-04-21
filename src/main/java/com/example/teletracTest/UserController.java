package com.example.teletracTest;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.teletracTest.TeletracTestApplication.SECRET;

@RestController
public class UserController {

    @PostMapping("/login")
    public String authenticate(@RequestParam("user") String username, @RequestParam("password") String password) { // In a real system user and password and check against DB
        return getJWTToken(username);
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        JwtBuilder token = Jwts
                .builder()
                .setId("")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000)).signWith(SECRET, SignatureAlgorithm.HS512);

        return "Bearer " + token.compact();
    }
}
