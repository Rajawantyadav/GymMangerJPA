package com.gymmanager.newgymmanager.service;


import com.gymmanager.newgymmanager.model.GymOwner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    String secretKey="0ab34a1f7b1f763dea26fca6c238d6c6b53668b6c4d70108b1fbffc0fea7cc7f3920cce72e4210594c13a9f72492f18e19a68b4a606e7f1aba69b94c104a8cf56d1ff1b14fd302a991ddc19028fb9fe3b5366f90716147a91050681a67625c9bdcf9b70a08d365e3f5a42fc25579943521801e0489a6e92e579a0c3d48031844dc770dfafb63e13fbc3094c86b7c98854667d3be3a2b7054a689c3573e9aa3a74d07b8df463054e003d3b5eaa953567e32d42a8a3d3c6cf253b7398aad04f8206097ced0e9bfea3d1804fae2a5e2f4d730e61b8d6f298623fd272b9714608931aae38a79f0bc45c23314a77b625aca9b4dc8baa52a28cddcceac26b370509634";

    public String getJwtSecretKey(){
        return secretKey;
    }
    public String generateToken(GymOwner user){

        Map<String,Object> claims=new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(user.getOwnerEmail())
                .issuer("DCB")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .and()
                .signWith(generateKey())
                .compact();

    }
    public SecretKey generateKey(){
        byte[] decode = Decoders.BASE64.decode(getJwtSecretKey());
        return Keys.hmacShaKeyFor(decode);

    }

    public String extractUserName(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaims(String jwtToken, Function<Claims,T> claimResolver) {
        Claims claims=extractClaimFromToken(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractClaimFromToken(String jwtToken) {
        return Jwts.parser().verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean isTokenIsValid(String jwtToken, UserDetails user) {
        String userName=extractUserName(jwtToken);
        if(userName.equals(user.getUsername()) && !isTokenExpired(jwtToken)){
            return true;
        }
        return false;
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date());
    }

    private Date extractExpirationDate(String jwtToken) {
        return extractClaims(jwtToken,Claims::getExpiration);
    }
}
