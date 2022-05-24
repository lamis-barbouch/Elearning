package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.ReclamationRepository;
import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.Reclamation;

@Service
public class ReclamationService {
	
	
	@Autowired
	ReclamationRepository reclamrep;
	
	
	public void addReclamation(Reclamation reclam) {
		reclamrep.save(reclam);
	
	}
	
	public List<Reclamation> listeReclamations() {
		List<Reclamation> listeReclamation=(List<Reclamation>) reclamrep.findAll();
		return listeReclamation;
	}
	
	
	public Reclamation repondreReclam(int id,String response) {
		Reclamation r=reclamrep.findById(id).get();
		 r.setResponse(response);
		 
		 
		 return reclamrep.save(r);
	}

}
