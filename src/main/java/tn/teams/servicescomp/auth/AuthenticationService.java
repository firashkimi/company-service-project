package tn.teams.servicescomp.auth;

import java.io.IOException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tn.teams.servicescomp.configsecurete.JwtService;
import tn.teams.servicescomp.dto.Response;
import tn.teams.servicescomp.entites.Role;
import tn.teams.servicescomp.entites.User;
import tn.teams.servicescomp.listener.RegistrationCompleteEvent;
import tn.teams.servicescomp.repository.UserRepository;
import tn.teams.servicescomp.token.Token;
import tn.teams.servicescomp.token.TokenRepository;
import tn.teams.servicescomp.token.TokenType;

import static tn.teams.servicescomp.sevices.UserService.applicationUrl;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ApplicationEventPublisher publisher;

  public ResponseEntity<Response>  register(RegisterRequest userRequest,final HttpServletRequest request) {

    boolean userExists = repository.findAll()
            .stream()
            .anyMatch(user -> userRequest.getEmail().equalsIgnoreCase(user.getEmail()));

    if (userExists) {
      return ResponseEntity.badRequest().body(Response.builder()
              .responseMessage("User with provided email  already exists!")
              .build());
    }
    var user = User.builder()
       .fullname(userRequest.getFullname())
        .email(userRequest.getEmail())
        .password(passwordEncoder.encode(userRequest.getPassword()))
        .role(Role.USER)
        //.role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

    return new ResponseEntity<>(
            Response.builder()

                    .responseMessage("Success! Please, check your email to complete your registration")
                    .email(savedUser.getEmail())
                    .build(),
            HttpStatus.CREATED
    );
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
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

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
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
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
