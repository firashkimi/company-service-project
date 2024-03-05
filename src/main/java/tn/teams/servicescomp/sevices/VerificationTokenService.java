package tn.teams.servicescomp.sevices;

import org.springframework.http.ResponseEntity;
import tn.teams.servicescomp.dto.Response;
import tn.teams.servicescomp.entites.User;
import tn.teams.servicescomp.entites.VerificationToken;

public interface VerificationTokenService {
    void saveUserVerificationToken(User user, String token);
    String validateToken(String token);
    ResponseEntity<Response> verifyEmail(String token);
    VerificationToken generateNewVerificationToken(String oldToken);
}