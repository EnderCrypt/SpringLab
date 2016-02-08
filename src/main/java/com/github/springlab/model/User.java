package com.github.springlab.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.springlab.statuses.UserStatus;

@Entity
public class User extends Id
{
	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String userNumber;

	@Column(name = "status", nullable = false)
	private int userStatus;

	@Column(nullable = false)
	private Team team;

	@OneToMany
	private Collection<WorkItem> workItems;

	protected User()
	{
	}

	public User(String firstName, String lastName, String username, String password, String userNumber)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.userNumber = userNumber;
		workItems = new HashSet<>();
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getUserNumber()
	{
		return userNumber;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public UserStatus getStatus()
	{
		return UserStatus.values()[userStatus];
	}

	public void setStatus(UserStatus userStatus)
	{
		this.userStatus = userStatus.ordinal();
	}

	public Team getTeam()
	{
		return team;
	}

	public void assignTeam(Team team)
	{
		this.team = team;
	}

	public Collection<WorkItem> getWorkItems()
	{
		return new HashSet<>(workItems);
	}

	public User addWorkItem(WorkItem workItem)
	{
		workItems.add(workItem);
		return this;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userNumber == null) ? 0 : userNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}
		if (other instanceof User)
		{
			User otherUser = (User) other;
			return getUsername().equals(otherUser.getUsername()) &&
					getUserNumber().equals(otherUser.getUserNumber());
		}
		return false;

	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
