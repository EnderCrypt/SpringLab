package com.github.springlab.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.springlab.model.Team;
import com.github.springlab.model.User;
import com.github.springlab.repository.TeamRepository;
import com.github.springlab.repository.UserRepository;
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

			Team team = new Team("yhc3l");
			User user = new User("john", "doe", "johnDoe", "123456", "1001");
			team.setStatus(TeamStatus.ACTIVE);
			user.assignTeam(team);

			teamRepo.save(team);
			userRepo.save(user);

		}

	}

}
