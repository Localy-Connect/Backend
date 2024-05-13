package ch.gibb.localy.service;


import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.entity.User;
import ch.gibb.localy.data.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class TokenService {

    private static final String SIGNING_KEY = "R8f#2zGx5LqP3rVmYj!NvT1cWbDzH4k";

    private final UserRepository userRepository;

    @Autowired
    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Token generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        String tokenString = Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes()))
                .compact();

        return new Token(tokenString, user);
    }

    public Optional<Token> validateToken(String token) {
        try {
            var claims = Jwts.parserBuilder().setSigningKey(SIGNING_KEY.getBytes()).build().parseClaimsJws(token).getBody();
            var userId = Integer.parseInt(claims.getSubject());
            return userRepository.findById(userId).map(user -> new Token(token, user));
        } catch (JwtException e) {
            return Optional.empty();
        }
    }
}
