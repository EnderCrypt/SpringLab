package com.github.springlab.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.github.springlab.statuses.TeamStatus;

@Entity
public class Team extends Id
{
	@Column
	private String name;
	@Column
	private int status;
	@Column
	private Collection<User> users;

	protected Team()
	{
	}

	public Team(String name)
	{
		this.name = name;
		users = new HashSet<>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TeamStatus getStatus()
	{
		return TeamStatus.values()[status];
	}

	public void setStatus(TeamStatus status)
	{
		this.status = status.ordinal();
	}

	public Collection<User> getUsers()
	{
		return new HashSet<>(users);
	}

	public void addUser(User user)
	{
		users.add(user);
	}

}
