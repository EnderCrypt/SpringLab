package com.github.springlab.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.springlab.statuses.IssueStatus;

@Embeddable
public class Issue
{
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int issueStatus;

	protected Issue()
	{
	}

	public Issue(WorkItem workItem, String description)
	{
		workItem.addIssue(this);
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

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
