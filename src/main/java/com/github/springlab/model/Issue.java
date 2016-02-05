package com.github.springlab.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.springlab.statuses.IssueStatus;

@Embeddable
public class Issue
{
	@Column(nullable = false)
	private String description;
	@OneToOne
	private WorkItem workItem;
	@Column(nullable = false)
	private int issueStatus;

	protected Issue()
	{
	}

	public Issue(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public WorkItem getWorkItem()
	{
		return workItem;
	}

	public void setWorkItem(WorkItem workItem)
	{
		this.workItem = workItem;
	}

	public IssueStatus getIssueStatus()
	{
		return IssueStatus.values()[issueStatus];
	}

	public void setIssueStatus(IssueStatus issueStatus)
	{
		this.issueStatus = issueStatus.ordinal();
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
