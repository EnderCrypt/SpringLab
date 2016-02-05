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
	@OneToOne
	private Issue issue;
	@Column(name = "item_status", nullable = false)
	private int itemStatus;

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

	public User getObject()
	{
		return user;
	}

	public void setObject(User user)
	{
		this.user = user;
	}

	public Issue getIssue()
	{
		return issue;
	}

	public void setIssue(Issue issue)
	{
		this.issue = issue;
	}

	public ItemStatus getItemStatus()
	{
		return ItemStatus.values()[itemStatus];
	}

	public void setItemStatus(ItemStatus ItemStatus)
	{
		this.itemStatus = ItemStatus.ordinal();
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
