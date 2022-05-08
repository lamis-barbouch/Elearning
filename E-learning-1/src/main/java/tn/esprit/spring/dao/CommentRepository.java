package tn.esprit.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.*;

public interface CommentRepository extends CrudRepository<Comment,Integer>{
}
