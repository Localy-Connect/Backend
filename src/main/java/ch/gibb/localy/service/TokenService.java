package ch.gibb.localy.service;


import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.data.repository.UserInfoRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@Transactional
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey signingKey;

    private final UserInfoRepository userRepository;

    @Autowired
    public TokenService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void init() {
        if (signingKey != null) {
            this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        } else this.signingKey = Keys.hmacShaKeyFor("9o2vYvK9Zxq1r3Td5u6N8sV0wYfGhJkLmN4OpQrStUsdsa".getBytes());
    }

    public Optional<Token> validateToken(String token) {
        try {
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();


            var userId = claims.get("user_id", Integer.class);
            System.out.println("Validating token for userId=" + userId);

            return userRepository
                    .findById(userId)
                    .map(user -> new Token(token, user));
        } catch (JwtException e) {
            return Optional.empty();
        }
    }
}
