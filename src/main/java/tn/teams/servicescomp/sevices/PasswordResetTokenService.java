package tn.teams.servicescomp.sevices;

import org.springframework.http.ResponseEntity;
import tn.teams.servicescomp.dto.ChangePasswordResetRequest;

public interface PasswordResetTokenService {
    ResponseEntity<String> verifyEmail(String email);

    ResponseEntity<String> verifyOtp(Integer otp, String email);

    ResponseEntity<String> changePasswordHandler(
            ChangePasswordResetRequest changePasswordResetRequest,
            String email
    );

}
