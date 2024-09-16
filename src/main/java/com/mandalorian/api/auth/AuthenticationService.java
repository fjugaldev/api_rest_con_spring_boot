package com.mandalorian.api.auth;

import com.mandalorian.api.auth.token.Token;
import com.mandalorian.api.auth.token.TokenRepository;
import com.mandalorian.api.auth.token.TokenType;
import com.mandalorian.api.auth.user.Role;
import com.mandalorian.api.auth.user.RoleRepository;
import com.mandalorian.api.auth.user.User;
import com.mandalorian.api.auth.user.UserRepository;
import com.mandalorian.api.shared.enums.ErrorCode;
import com.mandalorian.api.shared.exceptions.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signUp(SignUpRequest request) {
        Collection<Role> roles = new ArrayList<>();

        request.getRoles().stream().forEach(role -> {
            Role roleEntity = roleRepository.findByCode(role).orElseThrow(() -> new ApplicationException(
                ErrorCode.ROLE_NOT_FOUND_ERROR.value(),
                String.format("Role: %s not found", role),
                HttpStatus.BAD_REQUEST
            ));

            roles.add(roleEntity);
        });

        User user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(roles)
            .enabled(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        User savedUser = repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .user(user)
            .expired(false)
            .revoked(false)
            .build();

        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest credentials) {
        authenticateWithCredentials(credentials);


        var user = repository.findByEmail(credentials.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            // Invalidate each token (Expired and Revoked to TRUE)
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);

                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }

        return null;
    }

    private Authentication authenticateWithCredentials(AuthenticationRequest credentials) {
        try {
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
                )
            );
        } catch (BadCredentialsException ex) {
            throw new ApplicationException(
                ErrorCode.BAD_CREDENTIALS_ERROR.value(),
                "Email or password are invalid. Please check the credentials and try again.",
                HttpStatus.UNAUTHORIZED
            );
        }
    }
}
