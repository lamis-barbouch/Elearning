package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.formationService;


@RestController
public class formationController {

	@Autowired
	formationService formationservice;
	
	
	@PostMapping("/addFormation")//success
	@ResponseBody
	public void addFormation(@RequestBody Formation formation){
		formationservice.addFormation(formation);
		}
	
	
	@GetMapping(value="/formationLists")//success
	@ResponseBody
	public List<Formation> listeFormations(){
		return formationservice.listeFormations();
		}
	
	@PutMapping(value="/updateFormation/{id}")//success
	@ResponseBody
	public Formation updateformation(@PathVariable("id")int id,@RequestBody Formation formation){
		return formationservice.updateformation(id,formation);
	}
	
	@DeleteMapping("/deleteFormation/{id}")//success
	@ResponseBody
	public void deleteformation(@PathVariable("id") int id) {formationservice.deleteformation(id);}
	
	@GetMapping("/findbyIdFormation/{id}")//succes
	@ResponseBody
	public Formation findbyId (@PathVariable("id")int id){ 
		return formationservice.findbyId(id);
		
	}
	
	@GetMapping("/findFormationByName/{titre}")
	@ResponseBody
	public List <Formation> findFormationByName(@PathVariable("titre") String titre){
		return formationservice.findFormationByName(titre);
		
	}
	
	@GetMapping("/filterformation/{prixFormation}")
	@ResponseBody
	public List<Formation> filterformation(@PathVariable("prixFormation")double prixFormation){
		
		return formationservice.filterformation(prixFormation);
	}
	
	@GetMapping("/upcomingFormations") //success
	@ResponseBody
	public List<Formation> upcomingFormations(){
	return formationservice.upcomingFormations();
	}
	@GetMapping("/passedFormations")
	@ResponseBody
	public List<Formation> passedFormations(){
		return formationservice.passedFormations();
	}
//	public void refundUsers(int eid);
//	public List<String> displayBestFormationByViews();
	
	@GetMapping("/displayBestFormationByParticipations")
	@ResponseBody
	public List<String> displayBestFormationByParticipations(){
		return formationservice.displayBestFormationByParticipations();
	}
	
	@PostMapping(value="/participerFormation/{idApprenant}/{idFormation}") //success
	@ResponseBody
	public void participerFormation(@PathVariable("idApprenant")int idApprenant,@PathVariable("idFormation")int idFormation){
		
		formationservice.participerFormation(idApprenant, idFormation);
	}
	

	@GetMapping("/participationsList/{idFormation}") 
	@ResponseBody
	public List<User> participationsList(@PathVariable("idFormation")int idFormation){
		return formationservice.participationsList(idFormation);
	}

	@GetMapping("/formationListForUser/{idApprenant}") 
	@ResponseBody
	public List<Formation> FormationsParticipatedList(@PathVariable("idApprenant") int idApprenant){
		return formationservice.FormationsParticipatedList(idApprenant);
		
	}
	
	
	
	
	
	
}
