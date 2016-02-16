package com.github.springlab.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.springlab.statuses.IssueStatus;

@Entity
public class Issue extends Id
{
	@Column(nullable = false)
	private String description;

	@Column(name = "status", nullable = false)
	private int issueStatus;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	private WorkItem workItem;

	protected Issue()
	{
	}

	public Issue(WorkItem workItem, String description)
	{
		this.workItem = workItem;
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public IssueStatus getStatus()
	{
		return IssueStatus.values()[issueStatus];
	}

	public void setStatus(IssueStatus issueStatus)
	{
		this.issueStatus = issueStatus.ordinal();
	}

	public void setWorkItem(WorkItem workItem)
	{
		this.workItem = workItem;
	}

	public WorkItem getWorkItem()
	{
		return workItem;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
