package tn.esprit.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role,Integer>{

}
