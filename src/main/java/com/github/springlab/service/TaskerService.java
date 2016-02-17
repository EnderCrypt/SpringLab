package com.github.springlab.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springlab.model.Issue;
import com.github.springlab.model.Team;
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

	// ------------------USER------------------

	public void update(User user)
	{
		if (user.getUsername().length() > 10)
		{
			throw new InvalidUserException("Username cannot exceed 10 characters!"); // TODO:
																						// exception
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

	public List<User> getByTeam(Team team)
	{
		return userRepository.findByTeam(team);
	}

	public List<User> getByUserNumber(String userNumber)
	{
		return userRepository.findByUserNumber(userNumber);
	}

	public List<User> getByUsername(String username)
	{
		return userRepository.findByUsernameLike(username);
	}

	public List<User> getByLastNameLike(String lastName)
	{
		return userRepository.findByLastNameLike(lastName);
	}

	// -----------------WORKITEM-------------------

	private void update(WorkItem workItem)
	{
		// must have active user
		if (workItem.getAssignedUser().isActive() == false)
		{
			throw new InvalidUserException("The assigned user is inactive");
		}
		// user has maximum of 5 workitems
		int connectedWorkitems = workItemRepository.findByAssignedUser(workItem.getAssignedUser()).size();
		if (workItem.hasId())
		{
			if (workItemRepository.findOne(workItem.getId()) == null)
			{
				connectedWorkitems--;
			}
		}
		if (connectedWorkitems > 4)
		{
			throw new InvalidUserException("cannot store more than 5 workitems at any time");
		}
		// save
		workItemRepository.save(workItem);
	}

	// -------------------- ISSUE -------------------- //
	public void update(Issue issue)
	{
		if (issue.getWorkItem().getStatus() == ItemStatus.DONE)
		{
			issue.getWorkItem().setStatus(ItemStatus.UNSTARTED);
			issueRepository.save(issue);
		}
		else
		{
			throw new InvalidItemException("Cannot add issue to unfinished work item!");
		}
	}

	public Set<WorkItem> getItemsWithIssue()
	{
		Set<WorkItem> wItems = new HashSet<>();
		for (Issue issue : issueRepository.findAll())
		{
			wItems.add(issue.getWorkItem());
		}
		return wItems;
	}

}
