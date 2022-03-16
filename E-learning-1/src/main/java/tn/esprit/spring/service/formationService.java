package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.UserDao;
import tn.esprit.spring.dao.formationRepository;
import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.User;

@Service
public class formationService implements IFormationService {

	@Autowired
	formationRepository formationrep;
	
	@Autowired
	UserDao userrep;
	
	@Override
	public void addFormation(Formation formation) {
		formationrep.save(formation);
		
	}

	@Override
	public List<Formation> listeFormations() {
		List<Formation> listeFormations=(List<Formation>) formationrep.findAll();
		return null;
	}

	@Override
	public void updateformation(Formation formation) {
		formationrep.save(formation);
	}

	@Override
	public void deleteformation(int id) {
		formationrep.deleteById(id);
		
	}

	@Override
	public Formation findbyId(int id) {
		return formationrep.findById(id).get();
	}

	

	@Override
	public List<Formation> filterformation(double prixFormation) {
		return formationrep.filterformation(prixFormation);
	}

	@Override
	public List<Formation> upcomingFormations() { 
		List<Formation>list=formationrep.upcomingFormations();
		return list;
	}

	@Override
	public List<Formation> passedFormations() {
		List<Formation>list=formationrep.passedFormations();
		return list;
	}

	@Override
	public void refundUsers(int eid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	public List<String> displayBestFormationByParticipations()  {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> lp= new ArrayList<>();//nbre de participants
		List<Formation> listFormation = (List<Formation>) formationrep.findAll();
		
		for (Formation f : listFormation) {
			listId.add(f.getIdFormation());
			lp.add(f.getListUsers().size());
		}
		
		List<Integer> sortedList = new ArrayList<>(lp);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			int ind = listId.get(lp.indexOf(max));// prend nbre de users et retourne id d'formation corresspondant
			s =(i+1)+" -Formation : "+formationrep.findById(ind).get().getTitreFormation()+" avec "+max+" participations ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lp.set(lp.indexOf(max), -1);
		}
		
		return list;
	}



	@Override
	public List<String> displayBestFormationByViews(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void participerFormation(int idApprenant,int idFormation) {
		User u=userrep.findById(idApprenant).orElse(null);
		Formation f=formationrep.findById(idFormation).orElse(null);
		
		if(f.getNbDePlace()>=1){
			
				f.getListUsers().add(u);
				int nv_nbreDePlace=f.getNbDePlace()-1;
				f.setNbDePlace(nv_nbreDePlace);
				formationrep.save(f);
		}
		else 
		{System.out.println("The event is closed.thank you ");}
	}

	@Override
	public List<Formation> findFormationByName(String titre) {
		List<Formation> list=(List<Formation>)formationrep.findFormationByTitreFormation(titre);
		return list;
	}

	@Override
	public List<String> participationsList(int idFormation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> FormationsParticipatedList(int idApprenant) {
		// TODO Auto-generated method stub
		return null;
	}

}
