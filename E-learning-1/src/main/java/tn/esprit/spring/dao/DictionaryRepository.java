package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Dictionary;

@Repository
public interface DictionaryRepository extends CrudRepository <Dictionary,Integer> {

	public Dictionary findDictionaryByMot(String name);
	
	public void deleteDictionaryByMot(String name) ;

}
