package tn.teams.servicescomp.sevices;

import java.util.List;
import java.util.Optional;

import tn.teams.servicescomp.entites.ServicesComp;

public interface ServisescoService {
	void deleteServicesComp(Long id);
	List<ServicesComp> listeallServicesComp ();
	Optional<ServicesComp> findByIdServicesComp(Long id);
	ServicesComp saveServicesComp(ServicesComp ServicesComp);
	ServicesComp updateServicesComp( Long id,  ServicesComp ServicesComp);
}
