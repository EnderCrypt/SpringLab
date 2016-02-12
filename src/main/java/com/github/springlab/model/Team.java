package com.github.springlab.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.github.springlab.statuses.TeamStatus;

@Entity
public class Team extends Id
{
	@Column
	private String name;
	@Column(name = "status")
	private int teamStatus;

	protected Team()
	{
	}

	public Team(String name)
	{
		this.name = name;
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
		return TeamStatus.values()[teamStatus];
	}

	public void setStatus(TeamStatus teamStatus)
	{
		this.teamStatus = teamStatus.ordinal();
	}
}
