package com.github.springlab.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
	@Column(nullable = false)
	private String userNumber;
	@Column(name = "user_status", nullable = false)
	private int userStatus;
	@Column(nullable = false)
	private Team userTeam;
	@OneToOne
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

	public String getUserID()
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

	public UserStatus getUserStatus()
	{
		return UserStatus.values()[userStatus];
	}

	public void setUserStatus(UserStatus userStatus)
	{
		this.userStatus = userStatus.ordinal();
	}

	public Team getUserTeam()
	{
		return userTeam;
	}

	public void setUserTeam(Team userTeam)
	{
		this.userTeam = userTeam;
	}

	public Collection<WorkItem> getWorkItem()
	{
		return new ArrayList<>(workItems);
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
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		User other = (User) obj;
		if (userNumber == null)
		{
			if (other.userNumber != null) return false;
		}
		else if (!userNumber.equals(other.userNumber)) return false;
		if (username == null)
		{
			if (other.username != null) return false;
		}
		else if (!username.equals(other.username)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
