package tn.teams.servicescomp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.teams.servicescomp.entites.ServicesComp;
import tn.teams.servicescomp.sevices.ServisescoService;

@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {
	@Autowired
	private ServisescoService servisescoService;
   @DeleteMapping("/delete/{id}")
	public void deleteServicesComp(@PathVariable("id") Long id) {
		servisescoService.deleteServicesComp(id);
	}
   @GetMapping("/lister")
	public List<ServicesComp> listeallServicesComp() {
		return servisescoService.listeallServicesComp();
	}
   @GetMapping("/findbyid/{id}")
	public Optional<ServicesComp> findByIdServicesComp(@PathVariable("id") Long id) {
		return servisescoService.findByIdServicesComp(id);
	}
@PostMapping("/save")
	public ServicesComp saveServicesComp(@RequestBody  ServicesComp ServicesComp) {
		return servisescoService.saveServicesComp(ServicesComp);
	}
@PutMapping("/update/{id}")
	public ServicesComp updateServicesComp(@PathVariable("id") Long id,@RequestBody ServicesComp ServicesComp) {
		return servisescoService.updateServicesComp(id, ServicesComp);
	} 
	
	

}
