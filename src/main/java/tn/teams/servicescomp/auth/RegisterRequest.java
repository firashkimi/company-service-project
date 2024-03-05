package tn.teams.servicescomp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.teams.servicescomp.entites.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private String fullname;
	private String email;
	private String password;
	
  private Role role;
}
