package tn.teams.servicescomp.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomComp;
	private String telephComp;
	private String email;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "copany_services", joinColumns = @JoinColumn(name = "copany_id"), inverseJoinColumns = @JoinColumn(name = "services_id"))
	private List<ServicesComp> servicesComps = new ArrayList<>();

	public void addServices(ServicesComp sevices) {
		servicesComps.add(sevices);
	}
	public void deleteervices(ServicesComp sevices) {
		servicesComps.remove(sevices);
	}
}
