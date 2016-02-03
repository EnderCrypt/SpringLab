package com.github.springlab.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WorkItem
{
	@Column(nullable = false)
	private String topic;
	@Column(nullable = false)
	private String description;

	protected WorkItem()
	{
	}

	// completed statuses and implemented in User, started workitem then got
	// scared
}
