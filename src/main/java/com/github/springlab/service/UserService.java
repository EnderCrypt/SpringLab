package com.github.springlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.springlab.model.User;
import com.github.springlab.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository)
	{
		this.repository = repository;
	}

	public User save(User user)
	{
		return repository.save(user);
	}
}
