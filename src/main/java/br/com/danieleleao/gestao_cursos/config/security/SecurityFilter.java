package br.com.danieleleao.gestao_cursos.config.security;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        SecretKey key = Keys.hmacShaKeyFor("secret@3918FORTEsecret@3918FORTE".getBytes());
        if (header != null) {

            // verificação do token 
            var token = header.replace("Bearer ", "");

            try {
                var resultToken = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
                var userId = resultToken.getPayload().getSubject();
                request.setAttribute("userId", userId);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        resultToken.getPayload().getSubject(), null, null);

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        }

        filterChain.doFilter(request, response);

    }

}
