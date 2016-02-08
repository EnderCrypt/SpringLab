package com.github.springlab.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.springlab.model.Team;
import com.github.springlab.model.User;
import com.github.springlab.model.WorkItem;
import com.github.springlab.statuses.ItemStatus;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long>
{
	List<WorkItem> findByUser(User user);

	List<WorkItem> findByStaus(ItemStatus itemStatus);

	List<WorkItem> findByTeam(Team team);

	List<WorkItem> findByDescriptionLike(String description);

	List<WorkItem> findByTopicLike(String topic);
}
