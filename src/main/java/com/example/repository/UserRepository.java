package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.modal.User;

public interface UserRepository extends CrudRepository<User, Integer> 
{
	public User findByUsernameAndPassword(String username, String password);
}
