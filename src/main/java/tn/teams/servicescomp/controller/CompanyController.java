package tn.teams.servicescomp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.teams.servicescomp.dto.Companydto;
import tn.teams.servicescomp.entites.Company;
import tn.teams.servicescomp.sevices.CompaService;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
	private final CompaService compaService;
	
	@DeleteMapping("/delete/{id}")
	public void deleteCompany(@PathVariable("id") Long id) {
		compaService.deleteCompany(id);
	}
@GetMapping("/lister")
	public List<Company> listeallCompany() {
		return compaService.listeallCompany();
	}
@GetMapping("/findByIdCompany/{id}")
	public Optional<Company> findByIdCompany(@PathVariable("id") Long id) {
		return compaService.findByIdCompany(id);
	}
@PostMapping("/save")
	public Company addCompany(@RequestBody Companydto companydto) {
		return compaService.addCompany(companydto);
	}
@PutMapping("/update/{id}")
	public Company updateCompany(Long id, Company company) {
		return compaService.updateCompany(id, company);
	}

@PutMapping("/addservicestocompany/{compId}/{servicesId}")
public Company addservicestocompany(@PathVariable("compId") Long compId,@PathVariable("servicesId") Long servicesId){
	return compaService.addservicestocompany(compId, servicesId);
}
@PostMapping("/deleteservicestocompany/{compId}/{servicesId}")
public Company deleteservicestocompany(@PathVariable("compId") Long compId,@PathVariable("servicesId") Long servicesId){
	return compaService.deleteservicestocompany(compId, servicesId);
}
	
	


	
	
	

}
