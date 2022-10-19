package com.carolinedenis.projectmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.carolinedenis.projectmanager.models.Project;
import com.carolinedenis.projectmanager.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();
    Optional<User> findByEmail(String email);
    List<User> findAllByProgress(Project project);
    List<User> findByProgressNotContains(Project project);
}
