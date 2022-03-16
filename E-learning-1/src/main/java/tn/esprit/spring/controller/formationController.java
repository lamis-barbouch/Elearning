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
	
	@PutMapping(value="/updateFormation")
	@ResponseBody
	public void updateformation(@RequestBody Formation formation){
		formationservice.updateformation(formation);
	}
	
	@DeleteMapping("/deleteFormation/{id}")
	@ResponseBody
	public void deleteformation(@PathVariable("id") int id) {formationservice.deleteformation(id);}
	
	@GetMapping("/findbyIdFormation/{id}")
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
	
	@GetMapping("/upcomingFormations")
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
	
	//public List<String> participationsList(int idFormation);
	//public List<String> FormationsParticipatedList(int idApprenant);
	
	
	
	
	
	
	
	
	
}
