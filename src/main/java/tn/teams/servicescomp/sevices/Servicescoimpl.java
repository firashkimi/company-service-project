package tn.teams.servicescomp.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.teams.servicescomp.entites.ServicesComp;
import tn.teams.servicescomp.repository.ServicesComrepository;
@Service
public class Servicescoimpl implements ServisescoService {
	@Autowired
	private ServicesComrepository servicesComrepository;

	@Override
	public void deleteServicesComp(Long id) {
		servicesComrepository.deleteById(id);
	}

	@Override
	public List<ServicesComp> listeallServicesComp() {
		// TODO Auto-generated method stub
		return servicesComrepository.findAll();
	}

	@Override
	public Optional<ServicesComp> findByIdServicesComp(Long id) {
		
		return servicesComrepository.findById(id);
	}

	@Override
	public ServicesComp saveServicesComp(ServicesComp ServicesComp) {
		// TODO Auto-generated method stub
		return servicesComrepository.save(ServicesComp);
	}

	@Override
	public ServicesComp updateServicesComp(Long id, ServicesComp ServicesComp) {
		ServicesComp servicesbyid= servicesComrepository.findById(id).get();
		//servicesbyid.setId(id);
		servicesbyid.setNomService(ServicesComp.getNomService());
		return servicesComrepository.save(servicesbyid);
	}

}
