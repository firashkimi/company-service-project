package tn.teams.servicescomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.teams.servicescomp.entites.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
}
