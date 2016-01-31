package com.github.springlab.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.springlab.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
	List<User> findByUsername(String username);

	List<User> findByFirstName(String firstName);

	List<User> FindByFirstNameAndLastName(String firstName, String lastName);

	List<User> findByUserID(String userID);

	// Eller om vi använder oss av nån av dom under,
	// venne vad skillnaden är mellan Slice/Page och vad som är bäst

	// Page<User> findByLastNameLike(String lastName, Pageable pageable);
	// Slice<User> findByLastNameLike(String lastName, Pageable pageable);
	// @Query("select u from #{#entityName} u where u.userID = ?1")
	// List<user> getByUserID(String number);
}
