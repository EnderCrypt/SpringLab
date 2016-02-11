package com.github.springlab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.springlab.statuses.ItemStatus;

@Entity
public class WorkItem
{
	@Column(nullable = false)
	private String topic;

	@Column(nullable = false)
	private String description;

	@OneToOne
	private User user;

	@Column(name = "status", nullable = false)
	private int workItemStatus;

	protected WorkItem()
	{
	}

	public WorkItem(String topic, String description)
	{
		this.topic = topic;
		this.description = description;
	}

	public String getTopic()
	{
		return topic;
	}

	public String getDescription()
	{
		return description;
	}

	public User getAssignedUser()
	{
		return user;
	}

	public void assignUser(User user)
	{
		this.user = user;
	}

	public ItemStatus getStatus()
	{
		return ItemStatus.values()[workItemStatus];
	}

	public void setStatus(ItemStatus ItemStatus)
	{
		this.workItemStatus = ItemStatus.ordinal();
	}

	@Override
	public int hashCode()
	{
		final int prime = 37;
		int result = 1;
		result += prime * (getTopic() == null ? 0 : getTopic().hashCode());
		result += prime * (getDescription() == null ? 0 : getDescription().hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (obj instanceof WorkItem)
		{
			WorkItem otherObj = (WorkItem) obj;
			return getTopic().equals(otherObj.getTopic()) &&
					getDescription().equals(otherObj.getDescription()) &&
					getAssignedUser().equals(otherObj.getAssignedUser());
		}
		return false;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
