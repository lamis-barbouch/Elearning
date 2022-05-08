package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.DictionaryRepository;
import tn.esprit.spring.entity.Dictionary;
import tn.esprit.spring.exeptions.WordExistException;

@Service
public class DictionaryService implements IDictionaryService {

	
	@Autowired
	DictionaryRepository dictionaryrep;
	
	@Override
	public int addWord(Dictionary word) throws WordExistException {
		
		String mot=word.getMot().toLowerCase();
		word.setMot(mot);
		try{
		if (findDictionaryByMot(word.getMot())==null)
		{
			return dictionaryrep.save(word).getId_mot();
		}
		}
		catch (WordExistException e)
		{ System.out.println("Word already exist");};
		return 0;
		

		
	}

	@Override
	public List<Dictionary> DictionaryLists() {
		List<Dictionary> dictionaryLists =(List<Dictionary>) dictionaryrep.findAll();
		return dictionaryLists;
	}

	@Override
	public Dictionary updateDictionary(Dictionary word) {
		return dictionaryrep.save(word);
	}

	@Override
	public void deleteDictionary(int id) {
		dictionaryrep.deleteById(id);
		
	}

	@Override
	public Dictionary findDictionaryByMot(String name) {
		return dictionaryrep.findDictionaryByMot(name);
	}

	@Override
	public void deleteDictionaryByMot(String name) {
		dictionaryrep.deleteDictionaryByMot(name);
		
	}

}