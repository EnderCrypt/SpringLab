package com.github.springlab.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.springlab.model.Issue;
import com.github.springlab.model.Team;
import com.github.springlab.model.User;
import com.github.springlab.model.WorkItem;
import com.github.springlab.repository.IssueRepository;
import com.github.springlab.repository.TeamRepository;
import com.github.springlab.repository.UserRepository;
import com.github.springlab.repository.WorkItemRepository;
import com.github.springlab.statuses.ItemStatus;
import com.github.springlab.statuses.TeamStatus;

public class Main
{

	public static void main(String[] args)
	{
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext())
		{
			context.scan("com.github.springlab");
			context.refresh();

			UserRepository userRepo = context.getBean(UserRepository.class);
			TeamRepository teamRepo = context.getBean(TeamRepository.class);
			WorkItemRepository workRepo = context.getBean(WorkItemRepository.class);
			IssueRepository issueRepo = context.getBean(IssueRepository.class);

			Team team = new Team("yhc3l");
			User user = new User("john", "doe", "johnDoe", "123456", "1001");
			WorkItem item = new WorkItem("Add secret Pokemons!", "Requesting mystique");
			Issue issue = new Issue(item, "Weird encounter with \"missingno\". What is that?");

			team.setStatus(TeamStatus.ACTIVE);
			user.assignTeam(team);
			item.setStatus(ItemStatus.DONE);
			item.assignUser(user);

			teamRepo.save(team);
			userRepo.save(user);
			workRepo.save(item);
			issueRepo.save(issue);

			//			issueRepo.findAll().forEach(System.out::println);
			//			workRepo.findByAssignedUser_Team(team).forEach(System.out::println);
		}
	}

}
