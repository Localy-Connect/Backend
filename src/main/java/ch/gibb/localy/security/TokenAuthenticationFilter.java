package ch.gibb.localy.security;

import ch.gibb.localy.data.entity.Token;
import ch.gibb.localy.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Autowired
    public TokenAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        getTokenFromRequest(httpServletRequest)
                .flatMap(tokenService::validateToken)
                .ifPresent(this::setSecurityContext);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        var rawToken = request.getHeader("Authorization");
        if (StringUtils.hasText(rawToken) && rawToken.startsWith("Bearer ")) {
            return Optional.of(rawToken.substring(7));
        }
        return Optional.empty();
    }

    private void setSecurityContext(Token token) {
        var authentication = new UsernamePasswordAuthenticationToken(token.user(), token.token(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
