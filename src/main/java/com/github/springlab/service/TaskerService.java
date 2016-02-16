package com.github.springlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springlab.model.User;
import com.github.springlab.model.WorkItem;
import com.github.springlab.repository.IssueRepository;
import com.github.springlab.repository.TeamRepository;
import com.github.springlab.repository.UserRepository;
import com.github.springlab.repository.WorkItemRepository;
import com.github.springlab.statuses.ItemStatus;

public class TaskerService
{
	private UserRepository userRepository;
	private TeamRepository teamRepository;
	private WorkItemRepository workItemRepository;
	private IssueRepository issueRepository;

	@Autowired
	public TaskerService(UserRepository userRepository, TeamRepository teamRepository, WorkItemRepository workItemRepository, IssueRepository issueRepository)
	{
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
		this.workItemRepository = workItemRepository;
		this.issueRepository = issueRepository;
	}

	public void update(User user)
	{
		if (user.getUsername().length() > 10)
		{
			throw new InvalidUserException("Username cannot exceed 10 characters!"); // TODO: exception
		}
		if (user.isActive() == false)
		{
			List<WorkItem> workItems = workItemRepository.findByAssignedUser(user);
			for (WorkItem workItem : workItems)
			{
				workItem.setStatus(ItemStatus.UNSTARTED);
				update(workItem);
			}
		}
		userRepository.save(user);
	}

	public void update(WorkItem workItem)
	{

	}

}
