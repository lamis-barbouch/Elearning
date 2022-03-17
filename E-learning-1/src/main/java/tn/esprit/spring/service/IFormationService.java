package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.User;

public interface IFormationService {
	
	public void addFormation(Formation formation);
	public List<Formation> listeFormations();
	public Formation updateformation(int id,Formation formation);
	public void deleteformation(int id) ;
	public Formation findbyId(int id);
	public List <Formation> findFormationByName(String titre);
	public List<Formation> filterformation(double prixFormation);
	public List<Formation> upcomingFormations();
	public List<Formation> passedFormations();
	public void refundUsers(int eid);
	public List<String> displayBestFormationByViews();
	public List<String> displayBestFormationByParticipations();
	public void participerFormation(int idApprenant,int idFormation);
	
	//public List<String> participationsList(int idFormation);
	
	public List<User> participationsList(int idFormation);

	public List<Formation> FormationsParticipatedList(int idApprenant);
	
	

}
