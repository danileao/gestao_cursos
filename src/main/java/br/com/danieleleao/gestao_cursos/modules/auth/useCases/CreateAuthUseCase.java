package br.com.danieleleao.gestao_cursos.modules.auth.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.modules.auth.dto.CreateAuthRequest;
import br.com.danieleleao.gestao_cursos.modules.auth.dto.CreateAuthResponse;
import br.com.danieleleao.gestao_cursos.modules.users.UserRepository;
import br.com.danieleleao.gestao_cursos.modules.users.entities.RoleUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class CreateAuthUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateAuthResponse execute(CreateAuthRequest createAuthRequest) {

        // Verificar se email existe
        var user = this.userRepository.findByEmail(createAuthRequest.getEmail());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }

        if (!user.get().getRole().equals(RoleUser.PROFESSOR)) {
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }

        // Verificar se a senha está correta
        // 1234
        //8823uhjsdf8y2738dsf
        var passwordMatches = this.passwordEncoder.matches(createAuthRequest.getPassword(), user.get().getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Email ou senha incorretos");
        }

        //Gerar o token e retornar
        var issuedAt = Instant.now();
        var expiresIn = issuedAt.plus(Duration.ofMinutes(3));

        SecretKey key = Keys.hmacShaKeyFor("secret@3918FORTEsecret@3918FORTE".getBytes());

        Map<String, String> claims = new HashMap<>();
        claims.put("roles", RoleUser.PROFESSOR.toString());

        String token = Jwts.builder()
                .subject(user.get().getId().toString())
                .issuer("gestao_cursos")
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresIn))
                .claims(claims)
                .signWith(key)
                .compact();

        return CreateAuthResponse.builder()
                .acess_token(token)
                .created_at(Date.from(issuedAt))
                .expires_in(Date.from(expiresIn))
                .build();
    }

}
