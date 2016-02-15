package com.github.springlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.springlab.model.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long>
{
}
