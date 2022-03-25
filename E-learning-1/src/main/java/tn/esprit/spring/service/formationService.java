package tn.esprit.spring.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.NotificationRepository;
import tn.esprit.spring.dao.UserDao;
import tn.esprit.spring.dao.formationRepository;
import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.SmsRequest;
import tn.esprit.spring.entity.User;

@Service
public class formationService implements IFormationService {

	@Autowired
	formationRepository formationrep;
	
	@Autowired
	UserDao userrep;
	
	@Autowired
	NotificationRepository notifrep;
	
	@Autowired 
	SmsService smsService;
	
	@Autowired 
	NotifService notifService;
	
	LocalDateTime localDate = LocalDateTime.now();
	
	@Override
	public void addFormation(Formation formation) {
		formationrep.save(formation);
		
	}

	@Override
	public List<Formation> listeFormations() {
		List<Formation> listeFormations=(List<Formation>) formationrep.findAll();
		return listeFormations;
	}

	@Override
	public Formation updateformation(int id,Formation formation) {
		 Formation f=formationrep.findById(id).get();
		 f.setDateFormation(formation.getDateFormation());
		 f.setDescriptionFormation(formation.getDescriptionFormation());
		 f.setNbDePlace(formation.getNbDePlace());
		 f.setPrixFormation(formation.getPrixFormation());
		 f.setTitreFormation(formation.getTitreFormation());
		 
		 return formationrep.save(f);
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
				
				String msg="Bonjour Mr/Mme "+"  "+u.getNomUser()+" "+u.getPrenomUser()+" Vous avez participé au formation intitulé "+
						f.getTitreFormation()+"  "
						+" qui aura lieu "+f.getDateFormation()+"." +"Merci d'etre à l'heure" +" --- MEDIANET---- ";
						SmsRequest smsRequest = new SmsRequest ("+21656628701",msg);
						//send a mail sms to the client 
						smsService.sendSms(smsRequest );
		}
		else 
		{System.out.println("The event is closed.thank you ");}
	}

	@Override
	public List<Formation> findFormationByName(String titre) {
		List<Formation> list=(List<Formation>)formationrep.findFormationByTitreFormation(titre);
		return list;
	}

	/*
	@Override
	public List<String> participationsList(int idFormation) {
		Formation f=formationrep.findById(idFormation).orElse(null);
		List<User> listUser=new ArrayList<User>();
		listUser=f.getListUsers();
		List<String> lnoms=new ArrayList<String>();
		for(User u:listUser)
		{
			lnoms.add("Nom et prénom: "+u.getNomUser()+" "+u.getPrenomUser());
		}
		
		return lnoms;
		}
		*/

		
	@Override
	public List<User> participationsList(int idFormation) {
		Formation f=formationrep.findById(idFormation).orElse(null);
		List<User> listUser=new ArrayList<User>();
		listUser=f.getListUsers();
		List<User> listapprenant=new ArrayList<User>();
		for(User u:listUser)
		{
			if (u.getF()!=1){
				listapprenant.add(u);
			}
		}
		return listapprenant;
		}
		
		
	

	@Override
	public List<Formation> FormationsParticipatedList(int idApprenant) {
		User u=userrep.findById(idApprenant).orElse(null);
		List<Formation> listformation=new ArrayList<Formation>();
		listformation=u.getListformation();
		return listformation;
	}

	@Override
	public void affecterFormateurAFormation(int idFormateur, int idFormation) {
		
		User u=userrep.findById(idFormateur).orElse(null);
		Formation f=formationrep.findById(idFormation).orElse(null);
		
		f.getListUsers().add(u);
		
		
		String descriptionNotif = "Vous avez une nouvelle formation à présenter intitulé "+f.getTitreFormation();
		LocalDateTime localDate = LocalDateTime.now();
		
		Notification notification=new Notification(descriptionNotif,localDate);
		
		notification.getListUsers().add(u);
		
		notifrep.save(notification);
		notifService.sendNotif(idFormateur,notification);
		
		
		formationrep.save(f);
		userrep.save(u);
	}
	

}
