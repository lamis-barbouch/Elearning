package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Dictionary;
import tn.esprit.spring.service.DictionaryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class DictionaryController {
	
	@Autowired
	DictionaryService dictservice;
	
	
	@PostMapping("/addWord")
	@ResponseBody
	public int addWord (@RequestBody Dictionary word)
	{
		return dictservice.addWord(word);
	}
	
	@GetMapping(value="/DictionaryLists")//success
	@ResponseBody
	public List<Dictionary> DictionaryLists(){return dictservice.DictionaryLists();}
	
	@PutMapping(value="/updateDictionary")
	@ResponseBody
	public Dictionary updateDictionary(@RequestBody Dictionary word){return dictservice.updateDictionary(word);}
	
	@DeleteMapping("/deleteDictionary/{id}")
	@ResponseBody
	public void deleteDictionary(@PathVariable("id") int id)
	{
		dictservice.deleteDictionary(id);
	}
	
	@DeleteMapping("/deleteDictionary/{name}")
	@ResponseBody
	public void deleteDictionaryByMot(@PathVariable("name") String name)
	{
		dictservice.deleteDictionaryByMot(name);
	}
	
	@GetMapping("/findDictionaryByMot/{name}")
	@ResponseBody
	public Dictionary findDictionaryByMot(@PathVariable("name") String name)
	{
		return dictservice.findDictionaryByMot(name);
	}
	

}
