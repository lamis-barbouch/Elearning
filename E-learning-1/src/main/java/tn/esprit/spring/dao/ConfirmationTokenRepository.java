package tn.esprit.spring.dao;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.ConfirmationToken;


public interface  ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String>{
	ConfirmationToken findByConfirmationToken (String confirmationToken);
}