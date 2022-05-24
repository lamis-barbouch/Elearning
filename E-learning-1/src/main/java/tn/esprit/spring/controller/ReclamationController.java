package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.service.ReclamationService;

@RestController
public class ReclamationController {
	
	@Autowired
	ReclamationService reclamservice;
	
	
	@PostMapping("/addReclam")//success
	@ResponseBody
	public void addReclamation(@RequestBody Reclamation reclam){
		reclamservice.addReclamation(reclam);
		}
	
	
	@GetMapping(value="/reclamsLists")//success
	@ResponseBody
	public List<Reclamation> listeReclamations(){
		return reclamservice.listeReclamations();
				}
	
	@PutMapping(value="/repondreclam/{id}")//success
	@ResponseBody
	public Reclamation repondreReclam(@PathVariable("id")int id,@RequestBody String response){
		return reclamservice.repondreReclam(id, response);
	}
	

}
