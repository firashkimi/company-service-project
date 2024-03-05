package tn.teams.servicescomp.sevices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.teams.servicescomp.dto.Companydto;
import tn.teams.servicescomp.entites.Company;
import tn.teams.servicescomp.entites.ServicesComp;
import tn.teams.servicescomp.repository.CompRepository;
import tn.teams.servicescomp.repository.ServicesComrepository;


@Service
@RequiredArgsConstructor
public class CompaServiceimpl implements CompaService {

	private final CompRepository compRepository;
	private final ServicesComrepository repositoryservices;

	@Override
	public void deleteCompany(Long id) {
		compRepository.deleteById(id);

	}

	@Override
	public List<Company> listeallCompany() {

		return compRepository.findAll();
	}

	@Override
	public Optional<Company> findByIdCompany(Long id) {
		Optional<Company> company = compRepository.findById(id);
		if (company.isPresent()) {
			return company;
		} else

			return null;
	}

	

	@Override
	public Company addCompany(Companydto companydto) {
		Company company = new Company();
		company.setNomComp(companydto.getNomComp());
		company.setEmail(companydto.getEmail());
		company.setTelephComp(companydto.getTelephComp());
		if (companydto.getSevicesCom().isEmpty()) {
			throw new IllegalArgumentException("you need atleast on services");
		} else {
			List<ServicesComp> servicesComp = new ArrayList();
			for (Long secviceId : companydto.getSevicesCom()) {
				Optional<ServicesComp> servicess = repositoryservices.findById(secviceId);
				if (servicess.isPresent()) {
					servicesComp.add(servicess.get());
				}
			}
			company.setServicesComps(servicesComp);
			return compRepository.save(company);
		}
		
		
	}
	
	
	
	
	@Override
	public Company addservicestocompany(Long compId, Long servicesId) {
	    Company company = compRepository.findById(compId).orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + compId));
	    ServicesComp servicesComp = repositoryservices.findById(servicesId).orElseThrow(() -> new IllegalArgumentException("Service not found with id: " + servicesId));
	    
	    if (company.getServicesComps().contains(servicesComp)) {
	        throw new IllegalArgumentException("This service is already assigned to this company");
	    }

	    company.addServices(servicesComp);
	    return compRepository.save(company); // Assuming you want to save the updated company
	}
	@Override
	public Company deleteservicestocompany(Long compId, Long servicesId) {
	    Company company = compRepository.findById(compId).orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + compId));
	    ServicesComp servicesComp = repositoryservices.findById(servicesId).orElseThrow(() -> new IllegalArgumentException("Service not found with id: " + servicesId));
	    
	  /*  if (company.getServicesComps().contains(servicesComp)) {
	        throw new IllegalArgumentException("This service is already assigned to this company");
	    }
*/
	    company.deleteervices(servicesComp);
	    return compRepository.save(company); // Assuming you want to save the updated company
	}

	/* @Override
   public Company addservicestocompany(Long compId, Long servicesId) {
      Company company = compRepository.findById(compId).get();
      ServicesComp servicesComp = repositoryservices.findById(compId).get();;
      /* if (company.getServicesComps().contains(servicesComp.getNomService())) {
           throw new IllegalArgumentException("this Auteur is already assigned to this Livre");
       }
    
       company.addServices(servicesComp);
      
       
       return compRepository.save(company);
   }
	*/
	/*@Override
	public Company addservicestocompany(Long id,List<Long> companydto  ) {
		Company company = compRepository.findById(id).get();
		
		
		if (companydto.isEmpty()) {
			throw new IllegalArgumentException("you need atleast on services");
		} else {
			List<ServicesComp> servicesComp = new ArrayList();
			for (Long secviceId : companydto) {
				Optional<ServicesComp> servicess = repositoryservices.findById(secviceId);
				if (servicess.isPresent()) {
					servicesComp.add(servicess.get());
				}
			}
			
				company.setServicesComps(servicesComp);
			
			
			return compRepository.save(company);
		}
		
		
	}*/
	

	@Override
	public Company updateCompany(Long id, Company company) {
		Company compfindbyid = compRepository.findById(id).get();
		compfindbyid.setId(id);
		compfindbyid.setNomComp(company.getNomComp());
		compfindbyid.setEmail(company.getEmail());
		compfindbyid.setTelephComp(company.getTelephComp());
		Company compsaved = compRepository.save(compfindbyid);
		return compsaved;
	}

}
