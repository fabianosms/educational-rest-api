package com.machado.fabiano.forum.config.validation.security;

import com.machado.fabiano.forum.model.User;
import com.machado.fabiano.forum.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenServ tokenServ;
    private UserRepository userRepository;

    public TokenAuthenticationFilter(TokenServ tokenServ, UserRepository userRepository) {
        this.tokenServ = tokenServ;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoverToken(request);
        System.out.println(token);
        
        boolean isValid = tokenServ.isAValidToken(token);
        System.out.println(isValid);

        if (isValid) {
            authenticateClient(token);
        }
        
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {

        Long userId = tokenServ.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }
}
