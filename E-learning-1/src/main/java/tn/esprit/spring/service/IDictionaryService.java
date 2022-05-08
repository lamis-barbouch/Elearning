package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.entity.Dictionary;

public interface IDictionaryService {


	public int addWord(Dictionary word) ;

	public List<Dictionary> DictionaryLists() ;
	
	public Dictionary updateDictionary(Dictionary word) ;
	
	public void deleteDictionary(int id) ;
		
	
	public Dictionary findDictionaryByMot(String name) ;
	
	
	public void deleteDictionaryByMot(String name);
		
	}