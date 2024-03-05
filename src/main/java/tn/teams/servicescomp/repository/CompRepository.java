package tn.teams.servicescomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.teams.servicescomp.entites.Company;

public interface CompRepository extends JpaRepository<Company, Long>  {

}
