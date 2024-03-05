package tn.teams.servicescomp.dto;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.teams.servicescomp.entites.Company;
import tn.teams.servicescomp.entites.ServicesComp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Companydto {
	private Long id;
	private String  nomComp;
	private String telephComp;
	private String email;
	private List<Long> sevicesCom;
}
