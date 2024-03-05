package tn.teams.servicescomp.sevices;

import java.util.List;
import java.util.Optional;

import tn.teams.servicescomp.dto.Companydto;
import tn.teams.servicescomp.entites.Company;


public interface CompaService {
	void deleteCompany(Long id);
	List<Company> listeallCompany ();
	Optional<Company> findByIdCompany(Long id);
	Company addCompany(Companydto companydto);
	Company updateCompany( Long id,  Company company);
	//Company addservicestocompany(Long id,List<Long> companydto  );
	Company addservicestocompany(Long compId, Long servicesId);
	Company deleteservicestocompany(Long compId, Long servicesId) ;

}
