package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.Role;

@Repository
public interface formationRepository extends CrudRepository<Formation,Integer>{

	public List<Formation> findFormationByTitreFormation(String titre);
	
	@Query("SELECT f FROM Formation f WHERE f.prixFormation=:prixFormation")
	public List<Formation> filterformation(@Param ("prixFormation") double prixFormation);

	@Query("SELECT f FROM Formation f WHERE f.dateFormation >= CURRENT_DATE()")
	public List<Formation> upcomingFormations();
	
	@Query("SELECT f FROM Formation f WHERE f.dateFormation < CURRENT_DATE()")
	List<Formation> passedFormations();
	
}
