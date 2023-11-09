package com.smart.repository;

import org.springframework.data.repository.CrudRepository;

import com.smart.entities.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	
	

}
